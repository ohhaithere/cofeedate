package ru.ohhaithere.coffeedate.controller

import org.keycloak.KeycloakPrincipal
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.keycloak.representations.AccessToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.match.MatchCardDto
import ru.ohhaithere.coffeedate.dto.match.MatchDto
import ru.ohhaithere.coffeedate.service.MatchService
import java.security.Principal
import java.util.*


@RestController
@CrossOrigin
@RequestMapping("/match")
class MatchController(val service: MatchService) {

    @GetMapping("/{x}/{y}/")
    fun getMatches(@PathVariable x: Float, @PathVariable y: Float): List<MatchCardDto> {
        val user = SecurityContextHolder.getContext()
            .authentication.principal as KeycloakPrincipal<*>
        System.out.println(user.keycloakSecurityContext.token)
        return service.get(x, y, UUID.fromString(user.keycloakSecurityContext.token.preferredUsername))
    }

    @PostMapping
    fun save(@RequestBody dto: MatchDto): MatchDto {
        return service.save(dto)
    }

}