package com.example.demo.controller

import com.example.demo.model.Message
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebSocketEventListener(
    private val sendingOperations: SimpMessageSendingOperations
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectedEvent) {
        log.info("We have a new connection ma friend")
    }

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val username = headerAccessor.sessionAttributes?.get("username").toString()
        val message = Message(type = Message.MessageType.DISCONNECT, sender = username)

        sendingOperations.convertAndSend("/topic/public", message)
    }
}