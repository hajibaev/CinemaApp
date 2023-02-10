package com.example.data.cloud.models.video

import com.google.gson.annotations.SerializedName

data class VideoCloud(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val trailers: List<TrailerCloud>
)