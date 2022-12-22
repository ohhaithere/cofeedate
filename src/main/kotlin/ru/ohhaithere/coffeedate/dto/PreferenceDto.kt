package ru.ohhaithere.coffeedate.dto

import java.util.*

data class PreferenceDto(
    var id: UUID? = null,
    var userId: UUID? = null,
    val name: String
)
