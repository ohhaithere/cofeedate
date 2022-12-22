package ru.ohhaithere.coffeedate.controller

import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.DateDto
import ru.ohhaithere.coffeedate.service.DateService
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/date")
class DateController(val service: DateService) {

    @PostMapping
    fun save(@RequestBody dto: DateDto): DateDto {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    fun update(@RequestBody dto: DateDto): DateDto {
        return service.update(dto);
    }


    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): DateDto {
        return service.get(id);
    }

    @GetMapping
    fun get(): List<DateDto> {
        return service.get();
    }

}