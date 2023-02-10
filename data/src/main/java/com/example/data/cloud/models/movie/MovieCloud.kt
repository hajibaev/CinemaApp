package com.example.data.cloud.models.movie

import com.google.gson.annotations.SerializedName

data class MovieCloud(
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("genre_ids") val actorsId: List<Int>,
    @SerializedName("id") val movieId: Int,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("title") val movieTitle: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("popularity") val rating: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("video") val isHasVideo: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
)