package com.example.data.models.person

import com.example.data.models.movie.MovieData

class PersonData(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<MovieData>,
    val name: String,
    val popularity: Double
)