package com.example.mymovieapp.models.person

import android.os.Parcelable
import com.example.mymovieapp.models.movie.MovieUi
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonPresentation(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<MovieUi>,
    val name: String,
    val popularity: Double
) : Parcelable