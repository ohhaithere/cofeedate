package ru.ohhaithere.coffeedate.dto

import java.time.LocalDateTime
import java.util.*

data class DateDto (
    var id: UUID? = null,
    var userId1: UUID? = null,
    val userId2: UUID? = null,
    val placeId: UUID? = null,
    val status: String,
    val purpose: String,
    val time: LocalDateTime? = null
    )