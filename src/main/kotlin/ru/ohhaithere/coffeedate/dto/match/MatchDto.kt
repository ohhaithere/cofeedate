package ru.ohhaithere.coffeedate.dto.match

import java.time.LocalDateTime
import java.util.*

data class MatchDto(
    var id: UUID? = null,
    var dateId: UUID? = null,
    var userId: UUID? = null,
    var time: LocalDateTime? = null
)
