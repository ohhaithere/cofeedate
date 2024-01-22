package ru.ohhaithere.coffeedate.controller

import com.mashape.unirest.http.JsonNode
import jakarta.ws.rs.BadRequestException
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.AccessTokenResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.configuration.keycloak.KeycloakProvider
import ru.ohhaithere.coffeedate.dto.auth.LoginRequest
import ru.ohhaithere.coffeedate.dto.auth.RefreshTokenRequest
import ru.ohhaithere.coffeedate.dto.user.UserCreateDto
import ru.ohhaithere.coffeedate.service.KeycloakAdminClientService
import ru.ohhaithere.coffeedate.service.UserService
import javax.validation.constraints.NotNull

// TODO: оптимизировать метод login
@RestController
@CrossOrigin
@RequestMapping("/auth")
class AuthController(val kcProvider: KeycloakProvider,
                     val userService: UserService
) {

    @PostMapping("/sms")
    fun sms(@RequestBody phone: UserCreateDto): UserCreateDto {
        return userService.login(phone.phone)
    }

    @PostMapping("/login/")
    fun login(@RequestBody loginRequest: @NotNull LoginRequest?): ResponseEntity<AccessTokenResponse?>? {
        val user = userService.getUser(loginRequest!!.username, loginRequest.password)
        return ResponseEntity.ok(userService.getLogin(user!!.id.toString(), user.password!!))
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody refreshToken: @NotNull RefreshTokenRequest?): ResponseEntity<JsonNode?>? {
       return ResponseEntity.status(HttpStatus.OK).body(kcProvider.refreshToken(refreshToken!!.refreshToken))
    }

}