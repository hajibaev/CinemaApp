package com.example.data.cloud.models.movie

import com.google.gson.annotations.SerializedName

data class MovieDetailsCloud(
    @SerializedName("backdrop_path") val backdrop_path: String?,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val actors: List<GenresCloud>,
    @SerializedName("id") val movieId: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("status") val status: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)
