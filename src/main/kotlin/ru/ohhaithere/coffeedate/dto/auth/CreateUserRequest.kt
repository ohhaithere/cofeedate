package ru.ohhaithere.coffeedate.dto.auth

import java.util.*

data class CreateUserRequest(
    val id: UUID?,
    val phone: String,
    val refreshToken: String
)
