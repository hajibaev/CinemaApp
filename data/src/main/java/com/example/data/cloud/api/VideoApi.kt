package com.example.data.cloud.api

import com.example.data.cloud.models.video.VideoCloud
import com.example.data.cloud.server.Endpoints.TRAILER.GET_VIDEOS
import com.example.data.cloud.server.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApi {
    @GET(GET_VIDEOS)
    suspend fun getTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String
    ): Response<VideoCloud>
}