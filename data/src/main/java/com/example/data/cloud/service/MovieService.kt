package com.example.data.cloud.service

import com.example.data.cloud.models.movie.MoviesResponseCloud
import com.example.data.cloud.utils.Endpoints.LANGUAGE
import com.example.data.cloud.utils.Endpoints.Movie.NOW_PLAIN
import com.example.data.cloud.utils.Endpoints.Movie.POPULAR
import com.example.data.cloud.utils.Endpoints.Movie.TOP_RATED
import com.example.data.cloud.utils.Endpoints.Movie.TRENDING
import com.example.data.cloud.utils.Endpoints.Movie.UPCOMING
import com.example.data.cloud.utils.Utils
import com.example.data.cloud.utils.Utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(TRENDING)
    suspend fun getTrendingTodayMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int,
    ): Response<MoviesResponseCloud>

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int,
    ): Response<MoviesResponseCloud>

    @GET(NOW_PLAIN)
    suspend fun getNowPlainMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int,
    ): Response<MoviesResponseCloud>

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int,
    ): Response<MoviesResponseCloud>

    @GET(TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int,
    ): Response<MoviesResponseCloud>


    @GET("discover/movie")
    suspend fun getMovieGenres(
        @Query("with_genres") genres: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = LANGUAGE,
    ): Response<MoviesResponseCloud>


}