package com.example.data.cloud.models.movie

import com.google.gson.annotations.SerializedName

data class GenresCloud(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
