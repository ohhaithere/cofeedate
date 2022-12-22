package ru.ohhaithere.coffeedate.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ohhaithere.coffeedate.model.Date
import java.util.*

interface DateRepository: JpaRepository<Date, UUID> {
}