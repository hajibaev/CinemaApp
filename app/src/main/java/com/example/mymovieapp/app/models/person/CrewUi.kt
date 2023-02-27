package com.example.mymovieapp.app.models.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CrewUi(
    val profile_path: String?,
    val id: Int,
    val known_for_department: String,
    val popularity: Double,
    val original_name: String,

    ) : Parcelable


