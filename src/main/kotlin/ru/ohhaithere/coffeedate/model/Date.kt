package ru.ohhaithere.coffeedate.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "coffeedate", schema = "public")
class Date (

    @Id
    var id: UUID? = null,
    var userId1: UUID? = null,
    val userId2: UUID? = null,
    val placeId: UUID? = null,
    var purpose: String,
    val status: String,
    val x: Float,
    val y: Float,
    var time: LocalDateTime
)