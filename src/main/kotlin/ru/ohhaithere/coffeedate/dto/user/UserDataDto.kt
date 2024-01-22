package ru.ohhaithere.coffeedate.dto.user

import java.util.*

data class UserDataDto(
                       var workplace: String?,
                       var tags: String?,
                       var description: String?,
                       var photoUrl: List<String>,
                       var name: String?,
                       var id: UUID)
