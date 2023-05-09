package ru.ohhaithere.coffeedate.controller

import ru.ohhaithere.coffeedate.dto.UserCreateDto
import ru.ohhaithere.coffeedate.service.UserService
import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.UserDataDto
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @PostMapping
    fun save(@RequestBody createDto: UserCreateDto): UserCreateDto {
        return userService.save(createDto);
    }

   /* @PutMapping("/{id}/{code}")
    fun checkCode(@PathVariable id: UUID, @PathVariable code: String): UserDataDto {
        return userService.update(dataDto, id);
    } */

    @PutMapping("/{id}/")
    fun update(@RequestBody dataDto: UserDataDto, @PathVariable id: UUID): UserDataDto {
        return userService.update(dataDto, id);
    }


    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): UserDataDto {
        return userService.get(id);
    }

}