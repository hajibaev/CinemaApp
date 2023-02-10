package com.example.mymovieapp.models.movie

class TvSeriesResponseUi(
    val page: Int,
    val results: List<SeriesUi>,
    val total_pages: Int,
    val total_results: Int
)