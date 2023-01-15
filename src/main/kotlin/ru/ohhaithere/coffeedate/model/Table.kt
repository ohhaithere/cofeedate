package ru.ohhaithere.coffeedate.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "table", schema = "public")
data class Table(
    @Id
    var id: UUID? = null,
    var placeId: UUID,
    var x: Int,
    var y: Int
)