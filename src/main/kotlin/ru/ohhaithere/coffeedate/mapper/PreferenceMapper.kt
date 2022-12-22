package ru.ohhaithere.coffeedate.mapper

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import ru.ohhaithere.coffeedate.dto.PreferenceDto
import ru.ohhaithere.coffeedate.model.Preference

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface PreferenceMapper {

    fun convertToDto(preference: Preference): PreferenceDto
    @InheritInverseConfiguration
    fun convertToModel(preference: PreferenceDto): Preference

    fun convertToDtos(preferences: List<Preference>): List<PreferenceDto> {
        return preferences.map { t -> convertToDto(t) }
    }

}