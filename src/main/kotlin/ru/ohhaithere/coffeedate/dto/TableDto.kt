package ru.ohhaithere.coffeedate.dto

import java.util.*

data class TableDto (
    var id: UUID? = null,
    var placeId: UUID,
    var x: Int,
    var y: Int
)