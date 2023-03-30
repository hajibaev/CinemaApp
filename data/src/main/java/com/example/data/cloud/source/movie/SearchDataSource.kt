package com.example.data.cloud.source.movie

import com.example.data.data.models.movie.MoviesData
import com.example.data.data.models.movie.TvSeriesResponseData
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {

    fun fetchAllSearch(query: String): Flow<MoviesData>

}