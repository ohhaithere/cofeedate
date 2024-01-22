package ru.ohhaithere.coffeedate.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.ohhaithere.coffeedate.model.chat.ChatRoom
import java.util.*

@Repository
interface ChatRoomRepository: JpaRepository<ChatRoom, String> {

    fun findBySenderIdAndRecipientId(senderId: String?, recipientId: String?): Optional<ChatRoom?>?

}