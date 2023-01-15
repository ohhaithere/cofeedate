package ru.ohhaithere.coffeedate.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "place", schema = "public")
data class Place(
    @Id
    var id: UUID? = null,
    var name: String,
    var address: String,
    var description: String,
    var tags: String,
    var logoUrl: String,
    var created: LocalDateTime,
    var mapUrl: String
)
