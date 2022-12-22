package ru.ohhaithere.coffeedate.dto

import java.time.LocalDateTime
import java.util.*

data class PlaceCreateDto (
    var id: UUID? = null,
    var name: String,
    var password: String,
    var created: LocalDateTime
)