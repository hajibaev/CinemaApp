package com.example.mymovieapp.models.movie

class MoviesResponseUi(
    val currectPage: Int,
    val movies: List<MovieUi>,
    val total_results: Int,
    val totalPages: Int,
)