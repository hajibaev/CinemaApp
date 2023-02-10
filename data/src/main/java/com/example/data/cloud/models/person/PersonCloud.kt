package com.example.data.cloud.models.person

import com.example.data.cloud.models.movie.MovieCloud
import com.google.gson.annotations.SerializedName

data class PersonCloud(
     @SerializedName("profile_path") val profile_path: String?,
     @SerializedName("adult") val adult: Boolean,
     @SerializedName("id") val id: Int,
     @SerializedName("known_for") val known_for: List<MovieCloud>,
     @SerializedName("name") val name: String,
     @SerializedName("popularity") val popularity: Double
)

