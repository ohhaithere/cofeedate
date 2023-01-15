package ru.ohhaithere.coffeedate.dto

import java.util.*

data class PlaceDataDto(

    var name: String,
    var address: String,
    var description: String,
    var logoUrl: String,
    var mapUrl: String,
    var tags: String,
    var tables: List<TableDto>

)