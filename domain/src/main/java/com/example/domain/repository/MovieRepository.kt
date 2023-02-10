package com.example.domain.repository

import com.example.domain.DataRequestState
import com.example.domain.models.movie.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(page: Int): Flow<MoviesResponseDomain>
    fun getNowPlayingMovies(page: Int): Flow<MoviesResponseDomain>
    fun getUpcomingMovies(page: Int): Flow<MoviesResponseDomain>
    fun getTopRatedMovies(page: Int): Flow<MoviesResponseDomain>
    fun getTrendingMovies(page: Int): Flow<MoviesResponseDomain>
    fun getSearchMovies(query: String): Flow<MoviesResponseDomain>
    fun getRecommendationsMovies(movieId: Int): Flow<MoviesResponseDomain>
    fun getSimilarMovies(movieId: Int): Flow<MoviesResponseDomain>

    // Tv Movies
    suspend fun getDetails(movieId: Int): Flow<MovieDetailsDomain>
    suspend fun getActors(movieId: Int): Flow<CreditsResponseDomain>
    fun getTrendingTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    fun getTopRatedTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    fun getOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    fun getPopularTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    fun getAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    suspend fun getTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsDomain>
    fun getTvRecommendations(tvId: Int): Flow<TvSeriesResponseDomain>
    fun getTvSimilar(tvId: Int): Flow<TvSeriesResponseDomain>
    fun getFantasyMovies(page: Int, genres: String): Flow<TvSeriesResponseDomain>

}