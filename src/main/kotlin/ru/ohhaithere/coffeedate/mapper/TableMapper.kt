package ru.ohhaithere.coffeedate.mapper

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import ru.ohhaithere.coffeedate.dto.TableDto
import ru.ohhaithere.coffeedate.model.Table


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface TableMapper {

    fun convertToDto(table: Table): TableDto
    @InheritInverseConfiguration
    fun convertToModel(table: TableDto): Table

    fun convertToDtos(tables: List<Table>): List<TableDto> {
        return tables.map { t -> convertToDto(t) }
    }

}