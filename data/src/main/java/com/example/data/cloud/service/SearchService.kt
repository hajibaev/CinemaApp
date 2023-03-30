package com.example.data.cloud.service

import com.example.data.cloud.models.movie.MoviesResponseCloud
import com.example.data.cloud.models.movie.TvSeriesResponseCloud
import com.example.data.cloud.utils.Endpoints.LANGUAGE
import com.example.data.cloud.utils.Endpoints.Movie.SEARCH_MOVIE
import com.example.data.cloud.utils.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH_MOVIE)
    suspend fun getSearchMovies(
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("query") query: String,
    ): Response<MoviesResponseCloud>

}