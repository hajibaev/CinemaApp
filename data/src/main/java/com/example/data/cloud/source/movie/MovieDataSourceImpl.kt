package com.example.data.cloud.source.movie

import com.example.data.cloud.models.movie.*
import com.example.data.cloud.models.person.CreditsResponseCloud
import com.example.data.cloud.service.MovieService
import com.example.data.data.models.movie.*
import com.example.data.data.models.person.CreditsResponseData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val service: MovieService,
    private val mapListMovieCloudToData: Mapper<MoviesResponseCloud, MoviesData>,
    private val dispatchers: DispatchersProvider,
) : MovieDataSource {

    override fun getAllPopularMovies(page: Int): Flow<MoviesData> = flow {
        emit(service.getPopularMovies(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllNowPlayingMovies(page: Int): Flow<MoviesData> = flow {
        emit(service.getNowPlainMovies(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllUpcomingMovies(page: Int): Flow<MoviesData> = flow {
        emit(service.getUpcomingMovies(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllTopRatedMovies(page: Int): Flow<MoviesData> = flow {
        emit(service.getTopRatedMovies(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllTrendingTodayMovies(page: Int): Flow<MoviesData> = flow {
        emit(service.getTrendingTodayMovies(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllMovieGenres(page: Int, genres: String): Flow<MoviesData> = flow {
        emit(service.getMovieGenres(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())


}