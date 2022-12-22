package ru.ohhaithere.coffeedate.mapper

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import ru.ohhaithere.coffeedate.dto.DateDto
import ru.ohhaithere.coffeedate.model.Date

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface DateMapper {

    fun convertToDto(date: Date): DateDto
    @InheritInverseConfiguration
    fun convertToModel(date: DateDto): Date

    fun convertToDtos(dates: List<Date>): List<DateDto> {
        return dates.map { t -> convertToDto(t) }
    }

}