package ru.ohhaithere.coffeedate.dto

import java.util.*

data class UserCreateDto(val id: UUID?,
                         val name: String,
                         val email: String,
                         val password: String,)
