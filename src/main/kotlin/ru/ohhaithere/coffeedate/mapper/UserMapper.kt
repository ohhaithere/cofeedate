package ru.ohhaithere.coffeedate.mapper

import ru.ohhaithere.coffeedate.dto.UserCreateDto
import ru.ohhaithere.coffeedate.model.User
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import ru.ohhaithere.coffeedate.dto.UserDataDto

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface UserMapper {

    fun convertUserToDto(user: User): UserCreateDto
    @InheritInverseConfiguration
    fun convertUserToModel(userCreate: UserCreateDto): User

    fun convertDataToDto(user: User): UserDataDto
    @InheritInverseConfiguration
    fun convertDataToModel(userCreate: UserDataDto): User

    fun convertToDtos(users: List<User>): List<UserCreateDto> {
        return users.map { t -> convertUserToDto(t) }
    }

}