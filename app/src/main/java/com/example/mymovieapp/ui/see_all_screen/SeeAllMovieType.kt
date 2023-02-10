package com.example.mymovieapp.ui.see_all_screen

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class SeeAllMovieType : Parcelable {
    POPULAR,
    TOP_RATED,
    NOW_PLAYING,
    UPCOMING,
    TRENDING,
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