package ru.ohhaithere.coffeedate.service

import org.springframework.stereotype.Component
import ru.ohhaithere.coffeedate.dto.TableDto
import ru.ohhaithere.coffeedate.mapper.TableMapper
import ru.ohhaithere.coffeedate.repository.TableRepository
import java.util.*

//TODO: удалить
@Component
class TableService(private var repository: TableRepository,
                   private var mapper: TableMapper) {

    fun getAllTablesForPlace(placeId: UUID): List<TableDto> {
        return mapper.convertToDtos(repository.findAllByPlaceId(placeId))
    }

}