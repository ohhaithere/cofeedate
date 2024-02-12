package ru.ohhaithere.coffeedate.dto.match

import ru.ohhaithere.coffeedate.model.enum.MatchStatus
import java.time.LocalDateTime
import java.util.*

data class MatchDto(
    var id: UUID? = null,
    var dateId: UUID? = null,
    var userId: UUID? = null,
    var status: MatchStatus? = null,
    var time: LocalDateTime? = null
)
