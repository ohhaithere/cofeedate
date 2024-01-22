package ru.ohhaithere.coffeedate.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "match", schema = "public")
data class Match(
    @Id
    var id: UUID? = null,
    var dateId: UUID? = null,
    var userId: UUID? = null,
    var time: LocalDateTime? = null
)
