package com.example.data.storage.repository

import com.example.data.cloud.source.storage.StorageCloudDataSource
import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.SeriesData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.repository.MovieStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storage: StorageCloudDataSource,
    private val mapperMovieDomainToData: Mapper<MovieDomain, MovieData>,
    private val mapMovieDataToDomain: Mapper<MovieData, MovieDomain>,
    private val mapperSeriesDomainToData: Mapper<SeriesDomain, SeriesData>,
    private val mapSeriesDataToDomain: Mapper<SeriesData, SeriesDomain>,
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
