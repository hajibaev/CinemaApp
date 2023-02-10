package com.example.data.models.movie

class MoviesData(
    val currectPage: Int,
    val movies: List<MovieData>,
    val total_results: Int,
    val totalPages: Int,
)