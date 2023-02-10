package com.example.data.cloud.source.movie

import com.example.data.models.movie.*
import kotlinx.coroutines.flow.Flow

interface MoviesCloudDataSource {
    fun getAllPopularMovies(page: Int): Flow<MoviesData>
    fun getAllNowPlayingMovies(page: Int): Flow<MoviesData>
    fun getAllUpcomingMovies(page: Int): Flow<MoviesData>
    fun getAllTopRatedMovies(page: Int): Flow<MoviesData>
     fun getAllSearchMovies(query: String): Flow<MoviesData>
    fun getAllSimilarMovies(movieId: Int): Flow<MoviesData>
    fun getAllRecommendationsMovies(movieId: Int): Flow<MoviesData>
    fun getAllTrendingTodayMovies(page: Int): Flow<MoviesData>
    suspend fun getAllActors(movieId: Int): Flow<CreditsResponseData>
    suspend fun getAllDetails(movieId: Int): Flow<MovieDetailsData>

    // Tv Movies
    fun getAllTrendingTvSeries(page: Int): Flow<TvSeriesResponseData>
    fun getAllTopRatedTvSeries(page: Int): Flow<TvSeriesResponseData>
    fun getAllOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseData>
    fun getAllPopularTvSeries(page: Int): Flow<TvSeriesResponseData>
    fun getAllAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseData>
    suspend fun getAllTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsData>
    fun getAllTvSimilar(tvId: Int): Flow<TvSeriesResponseData>
    fun getAllTvRecommendations(tvId: Int): Flow<TvSeriesResponseData>

    // Movie Genres
    fun getAllFantasySeries(page: Int, genres: String): Flow<TvSeriesResponseData>
}