package ru.ohhaithere.coffeedate.dto

import java.util.*

data class PlaceDataDto(

    var name: String,
    var address: String,
    var description: String,
    var logoUrl: String,
    var map: String,
    var tags: String

)