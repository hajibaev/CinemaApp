package com.example.data.cloud.service

import com.example.data.cloud.models.movie.MovieDetailsCloud
import com.example.data.cloud.models.movie.MoviesResponseCloud
import com.example.data.cloud.models.person.CreditsResponseCloud
import com.example.data.cloud.utils.Endpoints
import com.example.data.cloud.utils.Endpoints.LANGUAGE
import com.example.data.cloud.utils.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDetailsService {

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<CreditsResponseCloud>


    @GET(Endpoints.Movie.MOVIE_DETAILS)
    suspend fun getDetailsMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<MovieDetailsCloud>


    @GET(Endpoints.Movie.SIMILAR)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<MoviesResponseCloud>

    @GET(Endpoints.Movie.RECOMMENDATIONS)
    suspend fun getRecommendationsMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<MoviesResponseCloud>

}