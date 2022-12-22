package ru.ohhaithere.coffeedate.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ohhaithere.coffeedate.model.Preference
import java.util.*

interface PreferenceRepository: JpaRepository<Preference, UUID> {
}