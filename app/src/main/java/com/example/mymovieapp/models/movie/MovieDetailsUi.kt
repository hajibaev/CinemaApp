package com.example.mymovieapp.models.movie

class MovieDetailsUi(
    val backdrop_path: String?,
    val budget: Int,
    val actors: List<MovieGenresPresentation>,
    val movieId: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val runtime: Int?,
    val status: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
