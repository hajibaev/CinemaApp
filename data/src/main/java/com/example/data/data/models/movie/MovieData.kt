package com.example.data.data.models.movie

class MovieData(
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String?,
    val actorsId: List<Int>,
    val movieId: Int,
    val originalTitle: String?,
    val originalLanguage: String,
    val movieTitle: String?,
    val backdropPath: String?,
    val rating: Double,
    val voteCount: Int,
    val isHasVideo: Boolean,
    val voteAverage: Double,
    val minimumAge: Int,
)