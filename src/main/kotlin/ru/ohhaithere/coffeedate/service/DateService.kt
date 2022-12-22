package ru.ohhaithere.coffeedate.service

import org.springframework.stereotype.Component
import ru.ohhaithere.coffeedate.dto.DateDto
import ru.ohhaithere.coffeedate.mapper.DateMapper
import ru.ohhaithere.coffeedate.repository.DateRepository
import java.time.LocalDateTime
import java.util.*

@Component
class DateService(private var mapper: DateMapper,
                  private var repository: DateRepository) {

    fun save(dto: DateDto): DateDto {
        val date = mapper.convertToModel(dto);
        date.id = UUID.randomUUID()
        date.time = LocalDateTime.now()
        repository.save(date);
        return mapper.convertToDto(date);
    }

    fun update(dto: DateDto): DateDto {
        val date = mapper.convertToModel(dto);
        repository.save(date);
        return mapper.convertToDto(date);
    }

    fun get(id: UUID): DateDto {
        return mapper.convertToDto(repository.getById(id));
    }

    fun get(): List<DateDto> {
        return mapper.convertToDtos(repository.findAll());
    }

}