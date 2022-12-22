package ru.ohhaithere.coffeedate.service

import org.springframework.stereotype.Component
import ru.ohhaithere.coffeedate.dto.PlaceCreateDto
import ru.ohhaithere.coffeedate.dto.PlaceDataDto
import ru.ohhaithere.coffeedate.mapper.PlaceMapper
import ru.ohhaithere.coffeedate.repository.PlaceRepository
import java.time.LocalDateTime
import java.util.*

@Component
class PlaceService(private var mapper: PlaceMapper,
                   private var repository: PlaceRepository) {

    fun save(dataDto: PlaceCreateDto): PlaceCreateDto {
        val place = mapper.convertCreateToModel(dataDto);
        place.id = UUID.randomUUID()
        place.created = LocalDateTime.now()
        repository.save(place);
        return mapper.convertCreateToDto(place);
    }

    fun update(dataDto: PlaceDataDto): PlaceDataDto {
        val place = mapper.convertDataToModel(dataDto);
        repository.save(place);
        return mapper.convertDataToDto(place);
    }

    fun get(id: UUID): PlaceDataDto {
        return mapper.convertDataToDto(repository.getById(id));
    }

    fun get(): List<PlaceDataDto> {
        return mapper.convertToDtos(repository.findAll());
    }

}