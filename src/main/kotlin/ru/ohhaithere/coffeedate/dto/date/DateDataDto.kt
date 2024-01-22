package ru.ohhaithere.coffeedate.dto.date

import java.time.LocalDateTime
import java.util.*

data class DateDataDto (

    var id: UUID? = null,
    var name: String? = null,
    val age: Int? = null,
    val description: String? = null,
    val workplace: String? = null,
    val photoUrl: String? = null,
    val distance: Int? = 0

)