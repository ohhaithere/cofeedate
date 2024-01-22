package ru.ohhaithere.coffeedate.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.ohhaithere.coffeedate.model.chat.ChatMessage
import ru.ohhaithere.coffeedate.model.enum.MessageStatus

interface ChatMessageRepository: JpaRepository<ChatMessage, String> {

    fun countBySenderIdAndRecipientIdAndStatus(
        senderId: String?, recipientId: String?, status: MessageStatus?
    ): Long

    fun findByChatId(chatId: String?): List<ChatMessage?>?

    @Query("UPDATE ChatMessage cm SET cm.status =:status WHERE cm.senderId =:senderId AND cm.recipientId =:recipientId")
    fun updateStatuses(status: MessageStatus, senderId: String, recipientId: String)

}