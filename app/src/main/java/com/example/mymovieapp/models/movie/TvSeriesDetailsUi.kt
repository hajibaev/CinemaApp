package com.example.mymovieapp.models.movie

class TvSeriesDetailsUi(
    val adult: Boolean,
    val backdropPath: String,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val genres: List<MovieGenresPresentation>,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val name: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int
)