package com.example.demo.model

import java.text.SimpleDateFormat
import java.util.Date

data class Message (
    val type: MessageType? = null,
    val content: String = "",
    val sender: String = "",
    val time: String = SimpleDateFormat().format(Date())
) {
    enum class MessageType {
        CHAT,
        CONNECT,
        DISCONNECT
    }
}