package ru.ohhaithere.coffeedate.repository

import ru.ohhaithere.coffeedate.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, UUID> {

    fun findByIdAndCode(id: UUID, code: String): User
    fun findFirstByPhone(phone: String): User

    fun findByIdIn(ids: MutableList<UUID?>): List<User>
}