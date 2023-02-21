package com.example.data.cloud.source.storage

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.SeriesData
import kotlinx.coroutines.flow.Flow

interface StorageCloudDataSource {
    suspend fun save(movie: MovieData)
    suspend fun delete(movieId: Int)
    fun getStorageMovies(): Flow<List<MovieData>>

    suspend fun saveTv(tv: SeriesData)
    suspend fun deleteTV(id: Int)
    fun getTvStorage(): Flow<List<SeriesData>>

}