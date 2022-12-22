package ru.ohhaithere.coffeedate.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ohhaithere.coffeedate.model.Place
import java.util.*

interface PlaceRepository: JpaRepository<Place, UUID> {
}