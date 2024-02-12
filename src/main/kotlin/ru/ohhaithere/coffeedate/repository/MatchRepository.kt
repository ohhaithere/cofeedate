package ru.ohhaithere.coffeedate.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.ohhaithere.coffeedate.model.Match
import java.util.UUID

@Repository
interface MatchRepository: JpaRepository<Match, UUID> {

    fun getMatchesByUserId(userId: UUID): List<Match>

}