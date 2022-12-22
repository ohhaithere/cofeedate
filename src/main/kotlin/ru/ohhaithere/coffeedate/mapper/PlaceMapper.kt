package ru.ohhaithere.coffeedate.mapper

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import ru.ohhaithere.coffeedate.dto.PlaceCreateDto
import ru.ohhaithere.coffeedate.dto.PlaceDataDto
import ru.ohhaithere.coffeedate.model.Place

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface PlaceMapper {

    fun convertDataToDto(place: Place): PlaceDataDto
    @InheritInverseConfiguration
    fun convertDataToModel(placeData: PlaceDataDto): Place

    fun convertCreateToDto(place: Place): PlaceCreateDto
    @InheritInverseConfiguration
    fun convertCreateToModel(place: PlaceCreateDto): Place

    fun convertToDtos(places: List<Place>): List<PlaceDataDto> {
        return places.map { t -> convertDataToDto(t) }
    }

}