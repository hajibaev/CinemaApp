package com.example.data.cloud.source.movie

import com.example.data.cloud.models.movie.*
import com.example.data.cloud.server.MovieApi
import com.example.data.data.models.movie.*
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesCloudDataImpl @Inject constructor(
    private val api: MovieApi,
    private val mapListMovieCloudToData: Mapper<MoviesResponseCloud, MoviesData>,
    private val mapDetailsCloudToData: Mapper<MovieDetailsCloud, MovieDetailsData>,
    private val mapCreditsResponseCloudToData: Mapper<CreditsResponseCloud, CreditsResponseData>,
    private val mapTvResponseCloudToData: Mapper<TvSeriesResponseCloud, TvSeriesResponseData>,
    private val mapTvDetailsCloudToData: Mapper<TvSeriesDetailsCloud, TvSeriesDetailsData>,
    private val dispatchers: DispatchersProvider,
) : MoviesCloudDataSource {

    override fun getAllPopularMovies(page: Int, genres: String): Flow<MoviesData> = flow {
        emit(api.getPopularMovies(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllNowPlayingMovies(page: Int, genres: String): Flow<MoviesData> = flow {
        emit(api.getNowPlainMovies(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())


    override fun getAllUpcomingMovies(page: Int, genres: String): Flow<MoviesData> = flow {
        emit(api.getUpcomingMovies(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())


    override fun getAllTopRatedMovies(page: Int, genres: String): Flow<MoviesData> = flow {
        emit(api.getTopRatedMovies(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllSearchMovies(query: String): Flow<MoviesData> = flow {
        emit(api.getSearchMovies(query = query))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())


    override fun getAllSimilarMovies(movieId: Int): Flow<MoviesData> = flow {
        emit(api.getSimilarMovies(movieId = movieId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllRecommendationsMovies(movieId: Int): Flow<MoviesData> = flow {
        emit(api.getRecommendationsMovies(movieId = movieId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())


    override fun getAllTrendingTodayMovies(page: Int, genres: String): Flow<MoviesData> = flow {
        emit(api.getTrendingTodayMovies(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())

    override suspend fun getAllActors(movieId: Int): Flow<CreditsResponseData> = flow {
        emit(api.getMovieCredits(movieId = movieId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapCreditsResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override suspend fun getAllTvActors(tvId: Int): Flow<CreditsResponseData> = flow {
        emit(api.getTvCredits(tvId = tvId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapCreditsResponseCloudToData::map)
        .flowOn(dispatchers.default())


    override suspend fun getAllDetails(movieId: Int): Flow<MovieDetailsData> = flow {
        emit(api.getDetailsMovies(movieId = movieId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapDetailsCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllMovieGenres(page: Int, genres: String): Flow<MoviesData> = flow {
        emit(api.getMovieGenres(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(dispatchers.default())


    // Tv Movies
    override fun getAllTrendingTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getTrendingTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllTopRatedTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getTopRatedTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getOnTheAirTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllPopularTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getPopularTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getAiringTodayTvSeries(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override suspend fun getAllTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsData> = flow {
        emit(api.getTvSeriesDetails(tvId = tvId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvDetailsCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllTvSimilar(tvId: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getTvSimilar(tvId = tvId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllTvRecommendations(tvId: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getTvRecommendations(tvId = tvId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())

    override fun getAllFantasySeries(page: Int, genres: String): Flow<TvSeriesResponseData> = flow {
        emit(api.getFantasySeries(page = page, genres = genres))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(dispatchers.default())
}