package ru.ohhaithere.coffeedate.controller

import khttp.responses.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.CredentialsDto
import ru.ohhaithere.coffeedate.dto.DateDto
import ru.ohhaithere.coffeedate.dto.TokenResponseDto
import ru.ohhaithere.coffeedate.service.KeycloakService
import java.net.http.HttpResponse

@RestController
@CrossOrigin
@RequestMapping("/keycloak")
class KeycloakController(val service: KeycloakService) {
    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody credentials: CredentialsDto): TokenResponseDto? {
        return service.getToken(credentials).body;
    }
}