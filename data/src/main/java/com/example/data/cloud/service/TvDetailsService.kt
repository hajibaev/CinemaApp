package com.example.data.cloud.service

import com.example.data.cloud.models.movie.TvSeriesDetailsCloud
import com.example.data.cloud.models.movie.TvSeriesResponseCloud
import com.example.data.cloud.models.person.CreditsResponseCloud
import com.example.data.cloud.utils.Endpoints.LANGUAGE
import com.example.data.cloud.utils.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvDetailsService {

    @GET("tv/{tv_id}/credits")
    suspend fun getTvCredits(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<CreditsResponseCloud>

    @GET("tv/{tv_id}")
    suspend fun getTvDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<TvSeriesDetailsCloud>


    @GET("tv/{tv_id}/similar")
    suspend fun getTvSimilar(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<TvSeriesResponseCloud>

    @GET("tv/{tv_id}/recommendations")
    suspend fun getTvRecommendations(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<TvSeriesResponseCloud>

}