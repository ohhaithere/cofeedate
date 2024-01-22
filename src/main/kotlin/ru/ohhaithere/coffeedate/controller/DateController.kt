package ru.ohhaithere.coffeedate.controller

import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.date.DateDto
import ru.ohhaithere.coffeedate.dto.date.CreateDateDto
import ru.ohhaithere.coffeedate.dto.date.DateDataDto
import ru.ohhaithere.coffeedate.service.DateService

@RestController
@CrossOrigin
@RequestMapping("/date")
class DateController(val service: DateService) {

    @PostMapping
    fun save(@RequestBody dto: CreateDateDto): CreateDateDto {
        return service.createDate(dto);
    }

    @PutMapping("")
    fun update(@RequestBody dto: DateDto): DateDto {
        return service.update(dto);
    }


    @GetMapping("/{x}/{y}/")
    fun get(@PathVariable x: Float, @PathVariable y: Float): List<DateDataDto> {
        return service.get(x, y);
    }

    @GetMapping
    fun get(): List<DateDto> {
        return service.get();
    }

}