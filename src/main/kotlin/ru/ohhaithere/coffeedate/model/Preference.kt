package ru.ohhaithere.coffeedate.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "preference", schema = "public")
class Preference (

    @Id
    var id: UUID? = null,
    var userId: UUID? = null,
    val name: String

)