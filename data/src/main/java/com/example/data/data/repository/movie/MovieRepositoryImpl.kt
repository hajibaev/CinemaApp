package com.example.data.data.repository.movie

import com.example.data.cloud.source.movie.MovieDataSource
import com.example.data.data.models.movie.MoviesData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val source: MovieDataSource,
    private val mapMoviesDataToDomain: Mapper<MoviesData, MoviesResponseDomain>,
    private val dispatchers: DispatchersProvider,
) : MovieRepository {

    override fun fetchAllPopularMovies(page: Int): Flow<MoviesResponseDomain> =
        source.getAllPopularMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllNowPlayingMovies(page: Int): Flow<MoviesResponseDomain> =
        source.getAllNowPlayingMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllUpcomingMovies(page: Int): Flow<MoviesResponseDomain> =
        source.getAllUpcomingMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllTopRatedMovies(page: Int): Flow<MoviesResponseDomain> =
        source.getAllTopRatedMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllTrendingMovies(page: Int): Flow<MoviesResponseDomain> =
        source.getAllTrendingTodayMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())

    override fun fetchAllMoviesGenres(page: Int, genres: String): Flow<MoviesResponseDomain> =
        source.fetchAllMovieGenres(page = page, genres = genres).map(mapMoviesDataToDomain::map)
            .flowOn(dispatchers.default())


}