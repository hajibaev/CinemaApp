package com.example.domain.repository

import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.SeriesDomain
import kotlinx.coroutines.flow.Flow

interface MovieStorageRepository {
    suspend fun save(movie: MovieDomain)
    suspend fun delete(movieId: Int)
    fun getStorageMovies(): Flow<List<MovieDomain>>

    suspend fun tvSave(tv: SeriesDomain)
    suspend fun tvDelete(tvId: Int)
    fun tvGetStorage(): Flow<List<SeriesDomain>>


}