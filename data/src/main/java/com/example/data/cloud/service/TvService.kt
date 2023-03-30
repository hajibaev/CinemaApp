package com.example.data.cloud.service

import com.example.data.cloud.models.movie.TvSeriesResponseCloud
import com.example.data.cloud.utils.Endpoints.LANGUAGE
import com.example.data.cloud.utils.Endpoints.Movie.AIRING_TODAY_TV
import com.example.data.cloud.utils.Endpoints.Movie.ON_THE_AIR_TV
import com.example.data.cloud.utils.Endpoints.Movie.POPULAR_TV
import com.example.data.cloud.utils.Endpoints.Movie.TOP_RATED_TV
import com.example.data.cloud.utils.Endpoints.Movie.TRENDING_TV
import com.example.data.cloud.utils.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvService {

    @GET(TRENDING_TV)
    suspend fun getTrendingTvSeries(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<TvSeriesResponseCloud>

    @GET(TOP_RATED_TV)
    suspend fun getTopRatedTvSeries(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<TvSeriesResponseCloud>


    @GET(ON_THE_AIR_TV)
    suspend fun getOnTheAirTvSeries(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<TvSeriesResponseCloud>


    @GET(POPULAR_TV)
    suspend fun getPopularTvSeries(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<TvSeriesResponseCloud>


    @GET(AIRING_TODAY_TV)
    suspend fun getAiringTodayTvSeries(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<TvSeriesResponseCloud>


    @GET("discover/tv")
    suspend fun getTvGenres(
        @Query("with_genres") genres: String,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int,
    ): Response<TvSeriesResponseCloud>

}