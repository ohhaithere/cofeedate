package ru.ohhaithere.coffeedate.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.ohhaithere.coffeedate.dto.chat.ChatNotification
import ru.ohhaithere.coffeedate.model.chat.ChatMessage
import ru.ohhaithere.coffeedate.service.ChatMessageService
import ru.ohhaithere.coffeedate.service.ChatRoomService

// TODO: кодстайл
@RestController
class ChatController {

    @Autowired
    private val messagingTemplate: SimpMessagingTemplate? = null

    @Autowired
    private val chatMessageService: ChatMessageService? = null

    @Autowired
    private val chatRoomService: ChatRoomService? = null

    @MessageMapping("/chat")
    fun processMessage(@Payload chatMessage: ChatMessage) {
        var chatId: String? = chatRoomService
            ?.getChatId(chatMessage.senderId, chatMessage.recipientId, true)
        chatMessage.chatId = chatId
        val saved = chatMessageService!!.save(chatMessage)
        chatMessage.recipientId?.let {
            messagingTemplate!!.convertAndSendToUser(
                it, "/queue/messages",
                ChatNotification(
                    saved!!.id,
                    saved.senderId,
                    saved.senderName
                )
            )
        }
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    fun countNewMessages(
        @PathVariable senderId: String?,
        @PathVariable recipientId: String?
    ): ResponseEntity<Long?>? {
        return ResponseEntity
            .ok(chatMessageService!!.countNewMessages(senderId, recipientId))
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    fun findChatMessages(
        @PathVariable senderId: String?,
        @PathVariable recipientId: String?
    ): ResponseEntity<*>? {
        return ResponseEntity
            .ok(chatMessageService!!.findChatMessages(senderId, recipientId))
    }


}