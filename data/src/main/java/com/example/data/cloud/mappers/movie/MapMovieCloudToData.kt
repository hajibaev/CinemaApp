package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.models.movie.MovieData
import com.example.domain.Maps

class MapMovieCloudToData : Maps<MovieCloud, MovieData> {
    override fun map(from: MovieCloud) = from.run {
        MovieData(
            posterPath = posterPath,
            adult = adult,
            overview = overview,
            releaseDate = releaseDate,
            actorsId = actorsId.map { ids -> ids },
            movieId = movieId,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            movieTitle = movieTitle,
            backdropPath = backdropPath,
            rating = rating,
            voteCount = voteCount,
            isHasVideo = isHasVideo,
            voteAverage = voteAverage,
        )
    }
}