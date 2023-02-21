package com.example.mymovieapp.ui.type

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class SeeAllMovieType : Parcelable {
    POPULAR,
    TOP_RATED,
    NOW_PLAYING,
    UPCOMING,
    TRENDING,
    DRAMATYPE,
    FAMILYTYPE,
    COMEDY,
    DOCUMENTARY,
    HISTORY,
    MYSTERY,
    WESTERN,
}

@Parcelize
enum class SeeAllTvType : Parcelable {
    POPULAR,
    TOP_RATED,
    AIRINGTODAY,
    ONTHEAIR,
    TRENDING,
    FAMILYTYPE,
    ANIMETYPE,
    DRAMATYPE,
    COMEDY,
    HISTORY,
    MYSTERY,
    WESTERN,
}