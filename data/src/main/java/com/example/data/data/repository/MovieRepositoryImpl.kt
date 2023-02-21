package com.example.data.data.repository

import com.example.data.cloud.source.movie.MoviesCloudDataSource
import com.example.data.data.models.movie.*
import com.example.domain.base.Mapper
import com.example.domain.models.movie.*
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesCloudDataSource: MoviesCloudDataSource,
    private val mapMoviesDataToDomain: Mapper<MoviesData, MoviesResponseDomain>,
    private val mapMovieDetailsToDomain: Mapper<MovieDetailsData, MovieDetailsDomain>,
    private val mapCreditsResponseDataDomain: Mapper<CreditsResponseData, CreditsResponseDomain>,
    private val mapTvResponseDataToDomain: Mapper<TvSeriesResponseData, TvSeriesResponseDomain>,
    private val mapTvDetailsDataToDomain: Mapper<TvSeriesDetailsData, TvSeriesDetailsDomain>,
) : MovieRepository {

    override fun getPopularMovies(page: Int): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllPopularMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getNowPlayingMovies(page: Int): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllNowPlayingMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getUpcomingMovies(page: Int): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllUpcomingMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getTopRatedMovies(page: Int): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllTopRatedMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getTrendingMovies(page: Int): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllTrendingTodayMovies(page = page).map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getSearchMovies(query: String): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllSearchMovies(query = query).map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)


    override fun getRecommendationsMovies(movieId: Int): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllRecommendationsMovies(movieId = movieId)
            .map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getSimilarMovies(movieId: Int): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllSimilarMovies(movieId = movieId).map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getMoviesGenres(page: Int, genres: String): Flow<MoviesResponseDomain> =
        moviesCloudDataSource.getAllMovieGenres(page = page, genres = genres)
            .map(mapMoviesDataToDomain::map)
            .flowOn(Dispatchers.Default)


    override suspend fun getDetails(movieId: Int): Flow<MovieDetailsDomain> =
        moviesCloudDataSource.getAllDetails(movieId = movieId).map(mapMovieDetailsToDomain::map)
            .flowOn(Dispatchers.Default)

    override suspend fun getActors(movieId: Int): Flow<CreditsResponseDomain> =
        moviesCloudDataSource.getAllActors(movieId = movieId)
            .map(mapCreditsResponseDataDomain::map)
            .flowOn(Dispatchers.Default)


    // Tv Movies
    override fun getTrendingTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        moviesCloudDataSource.getAllTrendingTvSeries(page = page)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getTopRatedTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        moviesCloudDataSource.getAllTopRatedTvSeries(page = page)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        moviesCloudDataSource.getAllOnTheAirTvSeries(page = page)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getPopularTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        moviesCloudDataSource.getAllPopularTvSeries(page = page).map(mapTvResponseDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        moviesCloudDataSource.getAllAiringTodayTvSeries(page = page)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override suspend fun getTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsDomain> =
        moviesCloudDataSource.getAllTvSeriesDetails(tvId = tvId)
            .map(mapTvDetailsDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getTvRecommendations(tvId: Int): Flow<TvSeriesResponseDomain> =
        moviesCloudDataSource.getAllTvRecommendations(tvId = tvId)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getTvSimilar(tvId: Int): Flow<TvSeriesResponseDomain> =
        moviesCloudDataSource.getAllTvSimilar(tvId = tvId).map(mapTvResponseDataToDomain::map)
            .flowOn(Dispatchers.Default)

    override fun getFantasyMovies(page: Int, genres: String): Flow<TvSeriesResponseDomain> =
        moviesCloudDataSource.getAllFantasySeries(page = page, genres = genres)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(Dispatchers.Default)
}