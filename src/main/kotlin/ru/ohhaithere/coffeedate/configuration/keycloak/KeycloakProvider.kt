package ru.ohhaithere.coffeedate.configuration.keycloak

import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class KeycloakProvider {

    @Value("\${keycloak.auth-server-url}")
    var serverURL: String? = null
    @Value("\${keycloak.realm}")
    var realm: String? = null
    @Value("\${keycloak.resource}")
    var clientID: String? = null
    @Value("\${keycloak.credentials.secret}")
    var clientSecret: String? = null
    private val keycloak: Keycloak? = null
    fun KeycloakProvider() {}

    fun getInstance(): Keycloak? {
        return keycloak
            ?: KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(serverURL)
                .clientId(clientID)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build()
    }


    fun newKeycloakBuilderWithPasswordCredentials(username: String?, password: String?): KeycloakBuilder? {
        return KeycloakBuilder.builder() //
            .realm(realm) //
            .serverUrl(serverURL) //
            .clientId(clientID) //
            .clientSecret(clientSecret) //
            .username(username) //
            .password(password)
    }

    @Throws(UnirestException::class)
    fun refreshToken(refreshToken: String?): JsonNode? {
        val url = "$serverURL/realms/$realm/protocol/openid-connect/token"
        return Unirest.post(url)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .field("client_id", clientID)
            .field("client_secret", clientSecret)
            .field("refresh_token", refreshToken)
            .field("grant_type", "refresh_token")
            .asJson().getBody()
    }

}