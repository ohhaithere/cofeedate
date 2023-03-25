package ru.ohhaithere.coffeedate.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user", schema = "public")
class User(
    @Id var id: UUID? = null,
    val name: String,
    val email: String,
    val password: String,
    var workplace: String?,
    var description: String?,
    var tags: String?,
    var photoUrl: String?,
    var searchGoal: String,
    var birthdate: LocalDate,
    var joined: LocalDateTime? = null
)
