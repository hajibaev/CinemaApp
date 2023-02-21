package com.example.mymovieapp.app.models.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CastUi(
    val adult: Boolean,
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int?,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?
) : Parcelable