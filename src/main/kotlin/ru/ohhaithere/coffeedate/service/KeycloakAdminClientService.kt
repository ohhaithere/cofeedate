package ru.ohhaithere.coffeedate.service

import jakarta.ws.rs.core.Response
import org.keycloak.admin.client.CreatedResponseUtil
import org.keycloak.admin.client.resource.UserResource
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.ohhaithere.coffeedate.configuration.keycloak.KeycloakProvider
import ru.ohhaithere.coffeedate.dto.auth.CreateUserRequest
import java.util.*

//TODO: пиздец, привести в порядок
@Service
class KeycloakAdminClientService(val kcProvider: KeycloakProvider) {

    @Value("\${keycloak.realm}")
    var realm: String? = null

    fun createKeycloakUser(user: CreateUserRequest): Response? {
        val usersResource: UsersResource? = kcProvider?.getInstance()?.realm(realm)?.users()
        val credentialRepresentation = createPasswordCredentials(user.refreshToken)
        val realmResource = kcProvider?.getInstance()?.realm(realm)
        val realmRoleUser = realmResource!!.roles()["zhopa-autist"].toRepresentation()
        val kcUser = UserRepresentation()
        kcUser.username = java.net.URLEncoder.encode(user.id.toString(), "UTF-8")
        kcUser.credentials = listOf(credentialRepresentation)
        kcUser.firstName = user.phone
        kcUser.lastName = user.phone
        kcUser.email = user.id.toString() + "@coffeedate.com"
        kcUser.isEnabled = true
        kcUser.isEmailVerified = false
        val response: Response = usersResource!!.create(kcUser)
        val userId = CreatedResponseUtil.getCreatedId(response)
        val userResource = usersResource[userId]
        userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser))
        userResource.update(userResource.toRepresentation())
        if (response.status == 201) {
            //If you want to save the user to your other database, do it here, for example:
//            User localUser = new User();
//            localUser.setFirstName(kcUser.getFirstName());
//            localUser.setLastName(kcUser.getLastName());
//            localUser.setEmail(user.getEmail());
//            localUser.setCreatedDate(Timestamp.from(Instant.now()));
//            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
//            usersResource.get(userId).sendVerifyEmail();
//            userRepository.save(localUser);
        }
        return response
    }

    /*fun updatePassword(login: String) {
        val kcUser = usersResource.toRepresentation()
        kcUser.username = user.phone
        kcUser.credentials = listOf(credentialRepresentation)
        kcUser.firstName = user.phone
        kcUser.lastName = user.phone
        kcUser.email = user.phone
        kcUser.isEnabled = true
        kcUser.isEmailVerified = false
        val response: Response = usersResource!!.create(kcUser)
        if (response.status == 201) { */
            //If you want to save the user to your other database, do it here, for example:
//            User localUser = new User();
//            localUser.setFirstName(kcUser.getFirstName());
//            localUser.setLastName(kcUser.getLastName());
//            localUser.setEmail(user.getEmail());
//            localUser.setCreatedDate(Timestamp.from(Instant.now()));
//            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
//            usersResource.get(userId).sendVerifyEmail();
//            userRepository.save(localUser);
       /* }
        return response
    } */

    private fun createPasswordCredentials(password: String): CredentialRepresentation {
        val passwordCredentials = CredentialRepresentation()
        passwordCredentials.isTemporary = false
        passwordCredentials.type = CredentialRepresentation.PASSWORD
        passwordCredentials.value = password
        return passwordCredentials
    }

}