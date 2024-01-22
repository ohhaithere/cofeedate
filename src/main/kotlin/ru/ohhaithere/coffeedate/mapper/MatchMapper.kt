package ru.ohhaithere.coffeedate.mapper

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import ru.ohhaithere.coffeedate.dto.match.MatchDto
import ru.ohhaithere.coffeedate.model.Match

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)

interface MatchMapper {

    fun convertToDto(match: Match): MatchDto

    @InheritInverseConfiguration
    fun convertToModel(date: MatchDto): Match

    fun convertToDtos(matches: List<Match>): List<MatchDto> {
        return matches.map { t -> convertToDto(t) }
    }

}