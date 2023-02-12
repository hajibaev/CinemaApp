package com.example.mymovieapp.models.movie

import android.os.Parcelable
import com.broadcast.myapplication.adapter.Item
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieUi(
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String?,
    var actorsId: List<Int>,
    val movieId: Int,
    val originalTitle: String?,
    val originalLanguage: String,
    val movieTitle: String?,
    val backdropPath: String?,
    val rating: Double,
    val voteCount: Int,
    val isHasVideo: Boolean,
    val voteAverage: Double,
) : Parcelable, Item