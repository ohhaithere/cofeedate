package ru.ohhaithere.coffeedate.mapper

import org.mapstruct.*
import ru.ohhaithere.coffeedate.dto.user.UserCreateDto
import ru.ohhaithere.coffeedate.model.User
import ru.ohhaithere.coffeedate.dto.user.UserDataDto

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface UserMapper {

    fun convertUserToDto(user: User): UserCreateDto
    @InheritInverseConfiguration
    fun convertUserToModel(userCreate: UserCreateDto): User

    @Mapping(target = "photoUrl", qualifiedByName = ["stringToArray"])
    fun convertDataToDto(user: User): UserDataDto

    fun convertToDtos(users: List<User>): List<UserCreateDto> {
        return users.map { t -> convertUserToDto(t) }
    }

    companion object {
        @Named("stringToArray")
        @JvmStatic
        fun stringToArray(photos: String): List<String> {
            return photos.split(",")
        }
    }
}