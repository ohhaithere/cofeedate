package ru.ohhaithere.coffeedate.model.chat

import org.mapstruct.Builder
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "chat_room", schema = "public")
data class ChatRoom(
    @Id
    var id: String? = null,
    var chatId: String? = null,
    var senderId: String? = null,
    val recipientId: String? = null
)
