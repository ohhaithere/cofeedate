package ru.ohhaithere.coffeedate.configuration.keycloak

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeycloakResolverConfig {

    @Bean
    fun KeycloakConfigResolver(): KeycloakConfigResolver? {
        return KeycloakSpringBootConfigResolver()
    }

}