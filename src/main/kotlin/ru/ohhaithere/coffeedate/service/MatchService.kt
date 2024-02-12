package ru.ohhaithere.coffeedate.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import ru.ohhaithere.coffeedate.dto.match.GetMatchesDto
import ru.ohhaithere.coffeedate.dto.match.MatchCardDto
import ru.ohhaithere.coffeedate.dto.match.MatchDto
import ru.ohhaithere.coffeedate.mapper.MatchMapper
import ru.ohhaithere.coffeedate.repository.DateRepository
import ru.ohhaithere.coffeedate.repository.MatchRepository
import ru.ohhaithere.coffeedate.repository.UserRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.util.UUID
import java.util.stream.Collectors

@Component
class MatchService(private var mapper: MatchMapper,
                   private var matchRepository: MatchRepository,
                   private var userRepository: UserRepository,
                   private var dateRepository: DateRepository) {

    fun save(matchDto: MatchDto): MatchDto {
        val match = mapper.convertToModel(matchDto)
        match.id = UUID.randomUUID()
        match.time = LocalDateTime.now()
        matchRepository.save(match)
        return mapper.convertToDto(match)
    }

    fun get(x: Float, y: Float, userId: UUID): List<MatchCardDto> {
        var dates = dateRepository.findAll()
        var userIds = dates.stream().map{x -> x.userId1}.collect(Collectors.toList())
        var matchesMade = matchRepository.getMatchesByUserId(userId)
        var userIdsToDelete = matchesMade.stream().map{x -> x.userId}.collect(Collectors.toList())
        userIds.removeAll(userIdsToDelete)
        var users = userRepository.findByIdIn(userIds)
        var matches = users.stream().map { x ->
             MatchCardDto(
                x.id,
                x.name,
                Period.between(x.birthdate, LocalDate.now()).years,
                x.description,
                x.workplace,
                 "http://158.160.55.62:8080/download/" + x.photoUrl,
                 x.tags?.split(","),
                0
            )
        }.collect(Collectors.toList())
        return matches
    }

}