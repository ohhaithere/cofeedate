package ru.ohhaithere.coffeedate.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ohhaithere.coffeedate.model.Table
import java.util.*

interface TableRepository: JpaRepository<Table, UUID> {

    fun findAllByPlaceId(placeId: UUID): List<Table>

}