package com.example.domain.repository

import com.example.domain.models.movie.TvSeriesResponseDomain
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun fetchAllTrending(page: Int): Flow<TvSeriesResponseDomain>

    fun fetchAllTopRated(page: Int): Flow<TvSeriesResponseDomain>

    fun fetchAllOnTheAir(page: Int): Flow<TvSeriesResponseDomain>

    fun fetchAllPopular(page: Int): Flow<TvSeriesResponseDomain>

    fun fetchAllAiringToday(page: Int): Flow<TvSeriesResponseDomain>

    fun fetchAllTvGenres(page: Int, genres: String): Flow<TvSeriesResponseDomain>

}