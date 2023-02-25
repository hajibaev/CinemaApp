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
    ACTION,
    ADVENTURE,
    ANIMATION,
    COMEDY,
    CRIME,
    DOCUMENTARY,
    DRAMA,
    FAMILY,
    FANTASY,
    HISTORY,
    HORROR,
    MUSIC,
    MYSTERY,
    ROMANCE,
    SCIENCEFICTION,
    TV_MOVIE,
    THRILLER,
    WAR,
    WESTERN
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
    CRIME,
    ACTION,
    DOCUMENTARY,
    KIDS,
    NEWS,
    REALITY,
    FANTASY,
    SOAP,
    TALK,
    POLITICS
}