package com.example.data.cloud.server

import androidx.annotation.IntRange
import com.example.data.cloud.models.movie.*
import com.example.data.cloud.utils.Endpoints.Movie.AIRING_TODAY_TV
import com.example.data.cloud.utils.Endpoints.Movie.MOVIE_DETAILS
import com.example.data.cloud.utils.Endpoints.Movie.NOW_PLAIN
import com.example.data.cloud.utils.Endpoints.Movie.ON_THE_AIR_TV
import com.example.data.cloud.utils.Endpoints.Movie.POPULAR
import com.example.data.cloud.utils.Endpoints.Movie.POPULAR_TV
import com.example.data.cloud.utils.Endpoints.Movie.RECOMMENDATIONS
import com.example.data.cloud.utils.Endpoints.Movie.SEARCH_MOVIE
import com.example.data.cloud.utils.Endpoints.Movie.SIMILAR
import com.example.data.cloud.utils.Endpoints.Movie.TOP_RATED
import com.example.data.cloud.utils.Endpoints.Movie.TOP_RATED_TV
import com.example.data.cloud.utils.Endpoints.Movie.TRENDING
import com.example.data.cloud.utils.Endpoints.Movie.TRENDING_TV
import com.example.data.cloud.utils.Endpoints.Movie.UPCOMING
import com.example.data.cloud.utils.Utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(TRENDING)
    suspend fun getTrendingTodayMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int,
    ): Response<MoviesResponseCloud>

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int,
    ): Response<MoviesResponseCloud>

    @GET(NOW_PLAIN)
    suspend fun getNowPlainMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int,
    ): Response<MoviesResponseCloud>

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int
    ): Response<MoviesResponseCloud>

    @GET(TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int
    ): Response<MoviesResponseCloud>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ): Response<CreditsResponseCloud>

    @GET(SEARCH_MOVIE)
    suspend fun getSearchMovies(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("query") query: String,
    ): Response<MoviesResponseCloud>

    @GET(MOVIE_DETAILS)
    suspend fun getDetailsMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "en",
    ): Response<MovieDetailsCloud>


    @GET(SIMILAR)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int = 1
    ): Response<MoviesResponseCloud>

    @GET(RECOMMENDATIONS)
    suspend fun getRecommendationsMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int = 1
    ): Response<MoviesResponseCloud>


    // TV Movies

    @GET(TRENDING_TV)
    suspend fun getTrendingTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int
    ): Response<TvSeriesResponseCloud>

    @GET(TOP_RATED_TV)
    suspend fun getTopRatedTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int
    ): Response<TvSeriesResponseCloud>


    @GET(ON_THE_AIR_TV)
    suspend fun getOnTheAirTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int
    ): Response<TvSeriesResponseCloud>


    @GET(POPULAR_TV)
    suspend fun getPopularTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int
    ): Response<TvSeriesResponseCloud>


    @GET(AIRING_TODAY_TV)
    suspend fun getAiringTodayTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") @IntRange(from = 1) page: Int
    ): Response<TvSeriesResponseCloud>


    @GET("tv/{tv_id}")
    suspend fun getTvSeriesDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): Response<TvSeriesDetailsCloud>


    @GET("tv/{tv_id}/similar")
    suspend fun getTvSimilar(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ): Response<TvSeriesResponseCloud>

    @GET("tv/{tv_id}/recommendations")
    suspend fun getTvRecommendations(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ): Response<TvSeriesResponseCloud>


    @GET("discover/tv")
    suspend fun getFantasySeries(
        @Query("with_genres") genres: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") lang: String = "en",
    ): Response<TvSeriesResponseCloud>

    @GET("discover/movie")
    suspend fun getMovieGenres(
        @Query("with_genres") genres: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") lang: String = "en",
    ): Response<MoviesResponseCloud>


}