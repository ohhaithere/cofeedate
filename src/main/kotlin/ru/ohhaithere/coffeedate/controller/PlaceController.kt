package ru.ohhaithere.coffeedate.controller

import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.PlaceCreateDto
import ru.ohhaithere.coffeedate.dto.PlaceDataDto
import ru.ohhaithere.coffeedate.service.PlaceService
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/place")
class PlaceController(val service: PlaceService) {

    @PostMapping
    fun save(@RequestBody dataDto: PlaceCreateDto): PlaceCreateDto {
        return service.save(dataDto);
    }

    @PutMapping("/{id}")
    fun update(@RequestBody dataDto: PlaceDataDto): PlaceDataDto {
        return service.update(dataDto);
    }


    @GetMapping("/{id}")
    fun get(@PathVariable id: UUID): PlaceDataDto {
        return service.get(id);
    }

    @GetMapping
    fun get(): List<PlaceDataDto> {
        return service.get();
    }

}