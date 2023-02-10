package com.example.domain.models.movie


class MoviesResponseDomain(
    val currectPage: Int,
    val movies: List<MovieDomain>,
    val total_results: Int,
    val totalPages: Int,
)

