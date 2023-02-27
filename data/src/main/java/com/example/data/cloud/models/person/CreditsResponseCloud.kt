package com.example.data.cloud.models.person

import com.google.gson.annotations.SerializedName

data class CreditsResponseCloud(
    @SerializedName("cast") val cast: List<CastCloud>,
    @SerializedName("crew") val crew: List<CrewCloud>,
    @SerializedName("id") val id: Int
)