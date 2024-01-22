package ru.ohhaithere.coffeedate.service

import org.springframework.stereotype.Component
import ru.ohhaithere.coffeedate.dto.date.DateDto
import ru.ohhaithere.coffeedate.dto.date.CreateDateDto
import ru.ohhaithere.coffeedate.dto.date.DateDataDto
import ru.ohhaithere.coffeedate.mapper.DateMapper
import ru.ohhaithere.coffeedate.model.Date
import ru.ohhaithere.coffeedate.model.User
import ru.ohhaithere.coffeedate.repository.DateRepository
import java.time.LocalDateTime
import java.util.*

//TODO: переписать нормально создание свиданок
@Component
class DateService(private var mapper: DateMapper,
                  private var userService: UserService,
                  private var repository: DateRepository) {

    fun createDate(dto: CreateDateDto): CreateDateDto {
        val date = mapper.convertCreateDateToModel(dto);
        date.id = UUID.randomUUID()
        date.time = LocalDateTime.now()
        repository.save(date);
        return mapper.covertToCreateDateDto(date);
    }

    fun update(dto: DateDto): DateDto {
        val date = mapper.convertToModel(dto);
        repository.save(date);
        return mapper.convertToDto(date);
    }

    // TODO: реализовать получение по точке
    fun get(x: Float, y: Float): List<DateDataDto> {
        val dates = repository.findAll()
        return dates.map {
            createDateData(it)
        }
    }

    fun createDateData(date: Date): DateDataDto {
        val user = date.userId1?.let { userService.get(it) }
        return DateDataDto(date.id,
            user?.name,
            22,
            user?.description,
            user?.workplace,
            user?.photoUrl?.get(0),
            10)
    }

    fun get(): List<DateDto> {
        return mapper.convertToDtos(repository.findAll());
    }

    fun getDatesNearby(y: Float, x: Float) {
        
    }

}