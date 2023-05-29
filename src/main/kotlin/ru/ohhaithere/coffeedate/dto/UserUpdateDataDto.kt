package ru.ohhaithere.coffeedate.dto

import org.springframework.web.multipart.MultipartFile

data class UserUpdateDataDto(var workplace: String?,
                             var tags: String?,
                             var description: String?,
                             var photos: List<MultipartFile>?,
                             var name: String?)
