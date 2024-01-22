package ru.ohhaithere.coffeedate.dto.user

import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

data class UserUpdateDataDto(var workplace: String?,
                             var tags: String?,
                             var description: String?,
                             var photos: List<MultipartFile>?,
                             var name: String?,
                             var sex: String?,
                             var birthdate: String)
