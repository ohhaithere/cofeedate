package ru.ohhaithere.coffeedate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ohhaithere.coffeedate.model.chat.ChatMessage
import ru.ohhaithere.coffeedate.model.enum.MessageStatus
import ru.ohhaithere.coffeedate.repository.ChatMessageRepository

//todo: кодстайл
@Service
class ChatMessageService(private var repository: ChatMessageRepository,
                         private var chatRoomService: ChatRoomService) {

    fun save(chatMessage: ChatMessage): ChatMessage? {
        chatMessage.status = MessageStatus.RECEIVED
        repository!!.save(chatMessage)
        return chatMessage
    }

    fun countNewMessages(senderId: String?, recipientId: String?): Long {
        return repository!!.countBySenderIdAndRecipientIdAndStatus(
            senderId, recipientId, MessageStatus.RECEIVED
        )
    }

    fun findChatMessages(senderId: String?, recipientId: String?): List<ChatMessage?>? {
        val chatId: String? = chatRoomService!!.getChatId(senderId, recipientId, false)
        val messages = repository!!.findByChatId(chatId)
        if (messages!!.size > 0) {
            if (senderId != null && recipientId != null) {
                    repository.updateStatuses(MessageStatus.DELIVERED, senderId, recipientId)
            }
        }
        return messages
    }

}