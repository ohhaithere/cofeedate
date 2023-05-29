package ru.ohhaithere.coffeedate.controller

import org.springframework.http.MediaType
import org.springframework.http.MediaType.MULTIPART_FORM_DATA
import ru.ohhaithere.coffeedate.dto.UserCreateDto
import ru.ohhaithere.coffeedate.service.UserService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.ohhaithere.coffeedate.dto.UserDataDto
import ru.ohhaithere.coffeedate.dto.UserUpdateDataDto
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/user")
class UserController(val userService: UserService) {

    val CONTENT_TYPE = "multipart/form-data"

    @PostMapping
    fun save(@RequestBody createDto: UserCreateDto): UserCreateDto {
        return userService.save(createDto);
    }

    @PostMapping("/{id}/{code}")
    fun checkCode(@PathVariable id: UUID, @PathVariable code: String): String {
        return "OK";
    }

    @PutMapping("/{id}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun update(@RequestParam("file") photos: List<MultipartFile>?,
               @RequestParam("workplace") workplace: String?,
               @RequestParam("tags") tags: String?,
               @RequestParam("description") description: String?,
               @RequestParam("name") name: String?, @PathVariable id: UUID): UserDataDto {
        return userService.update(UserUpdateDataDto(workplace, tags, description, photos, name), id);
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