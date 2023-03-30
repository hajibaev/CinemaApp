package com.example.domain.repository

import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.MoviesResponseDomain
import com.example.domain.models.person.CreditsResponseDomain
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    fun fetchAllMovieDetails(movieId: Int): Flow<MovieDetailsDomain>

    fun fetchAllRecommendations(movieId: Int): Flow<MoviesResponseDomain>

    fun fetchAllSimilar(movieId: Int): Flow<MoviesResponseDomain>

    fun fetchAllCredits(movieId: Int): Flow<CreditsResponseDomain>

}