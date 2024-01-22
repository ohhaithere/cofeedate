package ru.ohhaithere.coffeedate.dto.place

import org.springframework.data.geo.Point
import ru.ohhaithere.coffeedate.dto.TableDto

data class PlaceDataDto(

    var name: String,
    var address: String,
    var description: String,
    var logoUrl: String,
    var mapUrl: String,
    var tags: String,
    var tables: List<TableDto>,
    var location: Point

)