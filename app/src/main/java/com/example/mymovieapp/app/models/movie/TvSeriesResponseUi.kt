package com.example.mymovieapp.app.models.movie

class TvSeriesResponseUi(
    val page: Int,
    val results: List<SeriesUi>,
    val total_pages: Int,
    val total_results: Int
)