package com.example.domain.repository

import com.example.domain.models.movie.MoviesResponseDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun fetchAllPopularMovies(page: Int): Flow<MoviesResponseDomain>

    fun fetchAllNowPlayingMovies(page: Int): Flow<MoviesResponseDomain>

    fun fetchAllUpcomingMovies(page: Int): Flow<MoviesResponseDomain>

    fun fetchAllTopRatedMovies(page: Int): Flow<MoviesResponseDomain>

    fun fetchAllTrendingMovies(page: Int): Flow<MoviesResponseDomain>

    fun fetchAllMoviesGenres(page: Int, genres: String): Flow<MoviesResponseDomain>

}