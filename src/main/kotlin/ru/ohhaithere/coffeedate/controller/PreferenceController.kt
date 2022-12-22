package ru.ohhaithere.coffeedate.controller

import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.PreferenceDto
import ru.ohhaithere.coffeedate.service.PreferenceService
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/preference")
class PreferenceController(val service: PreferenceService) {

    @PostMapping
    fun save(@RequestBody dto: PreferenceDto): PreferenceDto {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    fun update(@RequestBody dto: PreferenceDto): PreferenceDto {
        return service.update(dto);
    }


    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): PreferenceDto {
        return service.get(id);
    }

    @GetMapping
    fun get(): List<PreferenceDto> {
        return service.get();
    }

}