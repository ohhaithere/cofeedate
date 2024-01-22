package ru.ohhaithere.coffeedate.service

import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.AccessTokenResponse
import ru.ohhaithere.coffeedate.dto.user.UserCreateDto
import ru.ohhaithere.coffeedate.mapper.UserMapper
import ru.ohhaithere.coffeedate.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import ru.ohhaithere.coffeedate.configuration.keycloak.KeycloakProvider
import ru.ohhaithere.coffeedate.dto.auth.CreateUserRequest
import ru.ohhaithere.coffeedate.dto.user.UserDataDto
import ru.ohhaithere.coffeedate.dto.user.UserUpdateDataDto
import ru.ohhaithere.coffeedate.model.User
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

//TODO: оптимизировать
@Component
class UserService(private var repository: UserRepository,
                  private var smsService: SmsService,
                  private var fileService: FileStorageService,
                  private var mapper: UserMapper,
                  private var keycloakClient: KeycloakAdminClientService,
                  private var kcProvider: KeycloakProvider) {

    fun save(createDto: UserCreateDto): UserCreateDto {
        val user = mapper.convertUserToModel(createDto)
        val code = smsService.sendSmsWithCode(createDto.phone)
        user.id = UUID.randomUUID()
        user.joined = LocalDateTime.now()
        user.registered = false
        user.code = code
        repository.save(user);
        return mapper.convertUserToDto(user);
    }

    fun getUser(phone: String, code: String): User? {
        val user = repository.findById(UUID.fromString(phone)).get()
        return user
    }

    fun login(phone: String): UserCreateDto {
        val user = repository.findFirstByPhone(phone)
        val code = smsService.sendSmsWithCode(phone)
        user.code = code
        repository.save(user)
        return mapper.convertUserToDto(user)
    }

    fun update(dataDto: UserUpdateDataDto, id: UUID): UserDataDto {
        var user = repository.getById(id)
        user.description = dataDto.description
        if (dataDto.photos != null) {
            user.photoUrl = fileService.upload(dataDto.photos)
        }
        user.tags = dataDto.tags
        user.workplace = dataDto.workplace
        user.sex = dataDto.sex
        //user.birthdate = dataDto.birthdate
        user.name = dataDto.name
        repository.save(user);
        return mapper.convertDataToDto(user);
    }

    fun checkCode(id: UUID, code: String): AccessTokenResponse? {
        val user = repository.findByIdAndCode(id, code)
        user.registered = true;
        val password = generatePassword()
        keycloakClient.createKeycloakUser(CreateUserRequest(
            user.id, user.phone, password
        ))
        user.password = password
        repository.save(user)
        return getLogin(user.id.toString(), password)
    }

    fun get(id: UUID): UserDataDto {
        return mapper.convertDataToDto(repository.getById(id));
    }

    fun generatePassword(): String {
        return UUID.randomUUID().toString()
    }

    fun getLogin(login: String, password: String): AccessTokenResponse? {
        val keycloak: Keycloak? =
            kcProvider.newKeycloakBuilderWithPasswordCredentials(login, password)
                ?.build()
        var accessTokenResponse: AccessTokenResponse? = keycloak!!.tokenManager().accessToken
        return accessTokenResponse
    }

    /* fun notifyUser(recipientUser: UserDataDto, notification: NotificationDto) {
            simpMessagingTemplate.convertAndSend("/topic/user.notification." + recipientUser.id, notification)
    } */


}