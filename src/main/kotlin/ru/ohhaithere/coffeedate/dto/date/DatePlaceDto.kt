package ru.ohhaithere.coffeedate.dto.date

import java.time.LocalDateTime
import java.util.*

data class DatePlaceDto(var id: UUID,
                        var userId1: UUID,
                        val placeId: UUID,
                        val time: LocalDateTime,
                        val placeName: String,
                        val placeY: Float,
                        val placeX: Float,
                        val placeLogoUrl: String
                        )
