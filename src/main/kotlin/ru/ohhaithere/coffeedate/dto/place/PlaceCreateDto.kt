package ru.ohhaithere.coffeedate.dto.place

import org.springframework.data.geo.Point
import java.time.LocalDateTime
import java.util.*

data class PlaceCreateDto (
    var id: UUID? = null,
    var name: String,
    var password: String,
    var created: LocalDateTime,
    var location: Point
)