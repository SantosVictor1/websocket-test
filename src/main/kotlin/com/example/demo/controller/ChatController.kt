package com.example.demo.controller

import com.example.demo.model.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Controller

@Controller
class ChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    fun send(message: Message): Message = message

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    fun newUser(message: Message, headerAccessor: SimpMessageHeaderAccessor): Message {
        headerAccessor.sessionAttributes?.set("username", message.sender)

        return message
    }
}