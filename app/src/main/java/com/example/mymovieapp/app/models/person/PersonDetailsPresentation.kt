package com.example.mymovieapp.app.models.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonDetailsPresentation(
    val known_for_department: String,
    val also_known_as: List<String>,
    val biography: String,
    val birthday: String?,
    val deathDay: String?,
    val gender: String,
    val id: Int,
    val name: String,
    val popularity: Double,
    val place_of_birth: String?,
    val profile_path: String?
):Parcelable