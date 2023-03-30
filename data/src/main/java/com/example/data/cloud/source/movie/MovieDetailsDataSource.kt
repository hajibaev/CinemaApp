package com.example.data.cloud.source.movie

import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MoviesData
import com.example.data.data.models.person.CreditsResponseData
import kotlinx.coroutines.flow.Flow

interface MovieDetailsDataSource {

    fun fetchAllMovieDetails(movieId: Int): Flow<MovieDetailsData>

    fun fetchSimilarMovies(movieId: Int): Flow<MoviesData>

    fun fetchRecommendationsMovies(movieId: Int): Flow<MoviesData>

    fun fetchAllCredits(movieId: Int): Flow<CreditsResponseData>

}