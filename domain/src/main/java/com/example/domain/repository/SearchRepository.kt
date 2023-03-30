package com.example.domain.repository

import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun fetchAllSearch(query: String): Flow<MoviesResponseDomain>

}