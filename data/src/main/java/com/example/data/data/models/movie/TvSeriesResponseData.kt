package com.example.data.data.models.movie

class TvSeriesResponseData(
    val page: Int,
    val results: List<SeriesData>,
    val total_pages: Int,
    val total_results: Int
)