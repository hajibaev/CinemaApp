package com.example.data.storage.repository

import com.example.data.cloud.source.storage.StorageCloudDataSource
import com.example.data.models.movie.MovieData
import com.example.data.models.movie.SeriesData
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.repository.MovieStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StorageRepositoryImpl(
    private val storage: StorageCloudDataSource,
    private val mapperMovieDomainToData: Maps<MovieDomain, MovieData>,
    private val mapMovieDataToDomain: Maps<MovieData, MovieDomain>,
    private val mapperSeriesDomainToData: Maps<SeriesDomain, SeriesData>,
    private val mapSeriesDataToDomain: Maps<SeriesData, SeriesDomain>,
) : MovieStorageRepository {

    override suspend fun save(movie: MovieDomain) =
        storage.save(movie = mapperMovieDomainToData.map(movie))


    override suspend fun delete(movieId: Int) =
        storage.delete(movieId = movieId)

    override fun getStorageMovies(): Flow<List<MovieDomain>> =
        storage.getStorageMovies().map { movies ->
            movies.map(mapMovieDataToDomain::map)
        }
    override suspend fun tvSave(tv: SeriesDomain) =
        storage.saveTv(tv = mapperSeriesDomainToData.map(tv))


    override suspend fun tvDelete(tvId: Int) =
        storage.deleteTV(id = tvId)

    override fun tvGetStorage(): Flow<List<SeriesDomain>> =
        storage.getTvStorage().map { movies ->
            movies.map(mapSeriesDataToDomain::map)
        }
}
