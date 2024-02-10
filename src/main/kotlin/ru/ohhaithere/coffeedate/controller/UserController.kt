package ru.ohhaithere.coffeedate.controller

import jakarta.ws.rs.BadRequestException
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.AccessTokenResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import ru.ohhaithere.coffeedate.dto.user.UserCreateDto
import ru.ohhaithere.coffeedate.service.UserService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.ohhaithere.coffeedate.configuration.keycloak.KeycloakProvider
import ru.ohhaithere.coffeedate.dto.auth.LoginRequest
import ru.ohhaithere.coffeedate.dto.user.UserDataDto
import ru.ohhaithere.coffeedate.dto.user.UserUpdateDataDto
import ru.ohhaithere.coffeedate.service.KeycloakAdminClientService
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.NotNull

@RestController
@CrossOrigin
@RequestMapping("/user")
class UserController(val userService: UserService ) {

    @PostMapping
    fun save(@RequestBody createDto: UserCreateDto): UserCreateDto {
        return userService.save(createDto);
    }

    @PostMapping("/{id}/{code}")
    fun checkCode(@PathVariable id: UUID, @PathVariable code: String): ResponseEntity<AccessTokenResponse?>? {
        return ResponseEntity.status(HttpStatus.OK).body(userService.checkCode(id, code));
    }

    @PutMapping("/{userId}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun update(@RequestParam("file") photos: List<MultipartFile>?,
               @RequestParam("workplace") workplace: String?,
               @RequestParam("tags") tags: String?,
               @RequestParam("aboutYourself") aboutYourself: String?,
               @RequestParam("sex") sex: String?,
               @RequestParam("birthdate") @DateTimeFormat(pattern = "dd.MM.yyyy") birthdate: LocalDate,
               @RequestParam("name") name: String?, @PathVariable userId: UUID): UserDataDto {
        return userService.update(UserUpdateDataDto(workplace, tags, aboutYourself, photos, name, sex, birthdate), userId);
    }

    @PutMapping("/{id}/photos", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadPhotos(@RequestParam("file") photo: List<MultipartFile>?,
                     @RequestParam("workplace") workplace: String?,
                     @RequestParam("tags") tags: String?,
                     @RequestParam("description") description: String?,
                     @RequestParam("name") name: String?,
                     @PathVariable id: UUID): String {
        return "";
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): UserDataDto {
        return userService.get(id);
    }



}