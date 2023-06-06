package ru.ohhaithere.coffeedate.service

import com.fasterxml.jackson.databind.ObjectMapper
import khttp.*
import khttp.responses.Response
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import ru.ohhaithere.coffeedate.dto.CredentialsDto
import ru.ohhaithere.coffeedate.dto.TokenResponseDto


@Component
class KeycloakService {


    val keycloakUrl: String = "http://158.160.52.151:8888/realms/coffee-date/protocol/openid-connect/token"

    fun getToken (credentialsDto: CredentialsDto): ResponseEntity<TokenResponseDto> {
        val values = mapOf("username" to credentialsDto.username, "password" to credentialsDto.password,
            "client_id" to "test-client", "grant_type" to "password")

        val objectMapper = ObjectMapper()
        val requestBody: String = objectMapper
            .writeValueAsString(values)


        var params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("client_id", "test-client")
        params.add("username", credentialsDto.username)
        params.add("password", credentialsDto.password)
        params.add("grant_type", "password")
        params.add("scope", "openid")

        var headers: HttpHeaders = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)

        var request: HttpEntity<MultiValueMap<String, String>> = HttpEntity(params, headers)

        return getTokenResponse(request, keycloakUrl)

    }

    private fun getTokenResponse (request: HttpEntity<MultiValueMap<String, String>>, url: String): ResponseEntity<TokenResponseDto> {
        val restTemplate = RestTemplate()
        val response: ResponseEntity<TokenResponseDto> =
            restTemplate.postForEntity(url, request, TokenResponseDto::class.java)
        return response
    }



}