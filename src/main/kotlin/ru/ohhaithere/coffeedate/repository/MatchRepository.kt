package ru.ohhaithere.coffeedate.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.ohhaithere.coffeedate.model.Match

@Repository
interface MatchRepository: JpaRepository<Match, String> {



}