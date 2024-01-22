package ru.ohhaithere.coffeedate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ohhaithere.coffeedate.model.chat.ChatRoom
import ru.ohhaithere.coffeedate.repository.ChatRoomRepository
import java.util.*

// TODO: кодстайл
@Service
class ChatRoomService {

    @Autowired
    private val chatRoomRepository: ChatRoomRepository? = null

    fun getChatId(
        senderId: String?, recipientId: String?, createIfNotExist: Boolean
    ): String? {
        var chatroom = chatRoomRepository!!.findBySenderIdAndRecipientId(senderId, recipientId)
        if (chatroom!!.isPresent) {
            return chatroom.get().chatId
        } else {
            val chatId = String.format("%s_%s", senderId, recipientId)
            var senderChatRoom = ChatRoom(
                chatId = chatId,
                senderId = senderId,
                recipientId = recipientId
            )
            var recipientChatRoom = ChatRoom(
                chatId = chatId,
                senderId = recipientId,
                recipientId = senderId
            )
            chatRoomRepository.save(senderChatRoom)
            chatRoomRepository.save(recipientChatRoom)
            return chatId
        }
    }
}