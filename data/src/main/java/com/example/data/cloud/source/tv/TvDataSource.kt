package com.example.data.cloud.source.tv

import com.example.data.data.models.movie.TvSeriesResponseData
import kotlinx.coroutines.flow.Flow

interface TvDataSource {

    fun fetchAllTrending(page: Int): Flow<TvSeriesResponseData>

    fun fetchAllTopRated(page: Int): Flow<TvSeriesResponseData>

    fun fetchAllOnTheAir(page: Int): Flow<TvSeriesResponseData>

    fun fetchAllPopular(page: Int): Flow<TvSeriesResponseData>

    fun fetchAllAiringToday(page: Int): Flow<TvSeriesResponseData>

    fun fetchAllTvGenres(page: Int, genres: String): Flow<TvSeriesResponseData>
}