package com.example.data.cloud.source.movie

import com.example.data.cloud.api.MovieApi
import com.example.data.cloud.models.movie.*
import com.example.data.cloud.source.handler.ResponseHandler
import com.example.data.models.movie.*
import com.example.domain.Maps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MoviesCloudDataImpl(
    private val api: MovieApi,
    private val mapListMovieCloudToData: Maps<MoviesResponseCloud, MoviesData>,
    private val mapDetailsCloudToData: Maps<MovieDetailsCloud, MovieDetailsData>,
    private val mapCreditsResponseCloudToData: Maps<CreditsResponseCloud, CreditsResponseData>,
    private val mapTvResponseCloudToData: Maps<TvSeriesResponseCloud, TvSeriesResponseData>,
    private val mapTvDetailsCloudToData: Maps<TvSeriesDetailsCloud, TvSeriesDetailsData>,
    private val responseHandler: ResponseHandler,
) : MoviesCloudDataSource {

    override fun getAllPopularMovies(page: Int): Flow<MoviesData> = flow {
        emit(api.getPopularMovies(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllNowPlayingMovies(page: Int): Flow<MoviesData> = flow {
        emit(api.getNowPlainMovies(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(Dispatchers.Default)


    override fun getAllUpcomingMovies(page: Int): Flow<MoviesData> = flow {
        emit(api.getUpcomingMovies(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(Dispatchers.Default)


    override fun getAllTopRatedMovies(page: Int): Flow<MoviesData> = flow {
        emit(api.getTopRatedMovies(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(Dispatchers.Default)

    override  fun getAllSearchMovies(query: String): Flow<MoviesData> = flow {
        emit(api.getSearchMovies(query = query))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(Dispatchers.Default)


    override fun getAllSimilarMovies(movieId: Int): Flow<MoviesData> = flow {
        emit(api.getSimilarMovies(movieId = movieId))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllRecommendationsMovies(movieId: Int): Flow<MoviesData> = flow {
        emit(api.getRecommendationsMovies(movieId = movieId))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapListMovieCloudToData::map)

    override fun getAllTrendingTodayMovies(page: Int): Flow<MoviesData> = flow {
        emit(api.getTrendingTodayMovies(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapListMovieCloudToData::map)
        .flowOn(Dispatchers.Default)

    override suspend fun getAllActors(movieId: Int): Flow<CreditsResponseData> = flow {
        emit(api.getMovieCredits(movieId = movieId))
    }.flowOn(Dispatchers.IO)
        .map { it.body()!! }.map(mapCreditsResponseCloudToData::map)
        .flowOn(Dispatchers.Default)


    override suspend fun getAllDetails(movieId: Int): Flow<MovieDetailsData> = flow {
        emit(api.getDetailsMovies(movieId = movieId))
    }.flowOn(Dispatchers.IO)
        .map { it.body()!! }.map(mapDetailsCloudToData::map)
        .flowOn(Dispatchers.Default)

    // Tv Movies
    override fun getAllTrendingTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getTrendingTvSeries(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllTopRatedTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getTopRatedTvSeries(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getOnTheAirTvSeries(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllPopularTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getPopularTvSeries(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getAiringTodayTvSeries(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(Dispatchers.Default)

    override suspend fun getAllTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsData> = flow {
        emit(api.getTvSeriesDetails(tvId = tvId))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvDetailsCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllTvSimilar(tvId: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getTvSimilar(tvId = tvId))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllTvRecommendations(tvId: Int): Flow<TvSeriesResponseData> = flow {
        emit(api.getTvRecommendations(tvId = tvId))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllFantasySeries(page: Int, genres: String): Flow<TvSeriesResponseData> = flow {
        emit(api.getFantasySeries(page = page, genres = genres))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapTvResponseCloudToData::map)
        .flowOn(Dispatchers.Default)
}