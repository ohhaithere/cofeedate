package ru.ohhaithere.coffeedate.dto.date

import java.time.LocalDateTime
import java.util.*

data class CreateDateDto(var id: UUID? = null,
                         var userId1: UUID? = null,
                         val placeId: UUID? = null,
                         val time: LocalDateTime? = null)
