package ru.ohhaithere.coffeedate.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class TokenResponseDto (
    @JsonProperty("access_token") val accessToken: String?,
    @JsonProperty("expires_in") val expiresIn: Int,
    @JsonProperty("refresh_expires_in") val refreshExpiresIn: Int?,
    @JsonProperty("refresh_token") val refreshToken: String?,
    @JsonProperty("token_type") val tokenType: String?,
    @JsonProperty("not_before_policy") val notBeforePolicy: Int,
    @JsonProperty("session_state") val sessionState: String,
    val scope: String
)
