package com.example.data.cloud.models.person

import com.google.gson.annotations.SerializedName

data class CrewCloud(
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("known_for_department") val known_for_department: String,
    @SerializedName("original_name") val original_name: String,
    @SerializedName("popularity") val popularity: Double,
)