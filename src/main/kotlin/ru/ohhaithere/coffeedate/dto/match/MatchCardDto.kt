package ru.ohhaithere.coffeedate.dto.match

import java.util.*

data class MatchCardDto(var userId: UUID? = null,
                        var name: String? = null,
                        val age: Int? = null,
                        val description: String? = null,
                        val workplace: String? = null,
                        val photoUrl: String? = null,
                        val tags: List<String>? = null,
                        val distance: Int? = 0)
