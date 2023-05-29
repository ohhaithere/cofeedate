package ru.ohhaithere.coffeedate.service

import ru.ohhaithere.coffeedate.dto.UserCreateDto
import ru.ohhaithere.coffeedate.mapper.UserMapper
import ru.ohhaithere.coffeedate.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import ru.ohhaithere.coffeedate.dto.UserDataDto
import ru.ohhaithere.coffeedate.dto.UserUpdateDataDto
import java.time.LocalDateTime
import java.util.*

@Component
class UserService(private var repository: UserRepository,
                  private var smsService: SmsService,
                  private var fileService: FileStorageService,
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

    fun update(dataDto: UserUpdateDataDto, id: UUID): UserDataDto {
        var user = repository.getById(id)
        user.description = dataDto.description
        if (dataDto.photos != null) {
            user.photoUrl = fileService.upload(dataDto.photos)
        }
        user.tags = dataDto.tags
        user.workplace = dataDto.workplace
        user.name = dataDto.name
        repository.save(user);
        return mapper.convertDataToDto(user);
    }

    fun updatePhotos(photos: List<MultipartFile>, id: UUID): UserDataDto {
        var user = repository.getById(id)
        user.photoUrl = fileService.upload(photos)
        repository.save(user);
        return mapper.convertDataToDto(user)
    }

    fun checkCode(id: UUID, code: String): UserDataDto {
        val user = repository.findByIdAndCode(id, code)
        user.registered = true;
        repository.save(user)
        return mapper.convertDataToDto(user)
    }

    fun get(id: UUID): UserDataDto {
        return mapper.convertDataToDto(repository.getById(id));
    }


}