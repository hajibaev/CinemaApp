package com.example.data.cloud.source.movie

import com.example.data.data.models.movie.*
import com.example.data.data.models.person.CreditsResponseData
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {

    fun getAllPopularMovies(page: Int): Flow<MoviesData>

    fun getAllNowPlayingMovies(page: Int): Flow<MoviesData>

    fun getAllUpcomingMovies(page: Int): Flow<MoviesData>

    fun getAllTopRatedMovies(page: Int): Flow<MoviesData>

    fun getAllTrendingTodayMovies(page: Int): Flow<MoviesData>

    fun fetchAllMovieGenres(page: Int, genres: String): Flow<MoviesData>

}