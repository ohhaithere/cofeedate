package ru.ohhaithere.coffeedate.service

import ru.ohhaithere.coffeedate.dto.UserCreateDto
import ru.ohhaithere.coffeedate.mapper.UserMapper
import ru.ohhaithere.coffeedate.repository.UserRepository
import org.springframework.stereotype.Component
import ru.ohhaithere.coffeedate.dto.UserDataDto
import java.time.LocalDateTime
import java.util.*

@Component
class UserService(private var repository: UserRepository,
                  private var smsService: SmsService,
                  private var mapper: UserMapper) {

    fun save(createDto: UserCreateDto): UserCreateDto {
        val user = mapper.convertUserToModel(createDto)
        val code = smsService.sendSmsWithCode(createDto)
        user.id = UUID.randomUUID()
        user.joined = LocalDateTime.now()
        user.code = code
        user.registered = false
        repository.save(user);
        return mapper.convertUserToDto(user);
    }

    fun update(dataDto: UserDataDto, id: UUID): UserDataDto {
        var user = repository.getById(id)
        user.description = dataDto.description
        user.photoUrl = dataDto.photoUrl
        user.tags = dataDto.tags
        user.workplace = dataDto.workplace
        repository.save(user);
        return mapper.convertDataToDto(user);
    }

    fun get(id: UUID): UserDataDto {
        return mapper.convertDataToDto(repository.getById(id));
    }

}