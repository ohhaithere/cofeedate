package ru.ohhaithere.coffeedate.controller

import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.dto.match.GetMatchesDto
import ru.ohhaithere.coffeedate.dto.match.MatchCardDto
import ru.ohhaithere.coffeedate.dto.match.MatchDto
import ru.ohhaithere.coffeedate.service.MatchService

@RestController
@CrossOrigin
@RequestMapping("/match")
class MatchController(val service: MatchService) {

    @GetMapping("/{x}/{y}/")
    fun getMatches(@PathVariable x: Float, @PathVariable y: Float): List<MatchCardDto> {
        return service.get(x, y)
    }

    @PostMapping
    fun save(@RequestBody dto: MatchDto): MatchDto {
        return service.save(dto)
    }

}