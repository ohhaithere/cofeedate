package ru.ohhaithere.coffeedate.model.chat

import ru.ohhaithere.coffeedate.model.enum.MessageStatus
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "chat_message", schema = "public")
data class ChatMessage(

 @Id
 val id: String? = null,
 var chatId: String? = null,
 val senderId: String? = null,
 val recipientId: String? = null,
 val senderName: String? = null,
 val recipientName: String? = null,
 val content: String? = null,
 val timestamp: Date? = null,
 var status: MessageStatus? = null
)
