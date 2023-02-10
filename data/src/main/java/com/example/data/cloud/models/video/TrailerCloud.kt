package com.example.data.cloud.models.video

import com.google.gson.annotations.SerializedName

data class TrailerCloud(
    @SerializedName("name") val trailerName: String,
    @SerializedName("key") val trailerKey: String,
    @SerializedName("official") val isOfficial: Boolean
)