package com.example.data.cloud.models.movie

import com.google.gson.annotations.SerializedName

data class MoviesResponseCloud(
    @SerializedName("page") val currectPage: Int,
    @SerializedName("results") val movies: List<MovieCloud>,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("total_pages") val totalPages: Int,
)
