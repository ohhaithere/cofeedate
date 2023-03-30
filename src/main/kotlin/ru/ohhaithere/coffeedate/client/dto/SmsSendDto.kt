package ru.ohhaithere.coffeedate.client.dto

data class SmsSendDto(
    var number: String,
    var sign: String,
    var text: String
)
