package com.example.data.storage.movie.mappers

import com.example.data.models.movie.MovieData
import com.example.data.storage.movie.MovieStorage
import com.example.domain.Maps

class MapMovieDataToStorage : Maps<MovieData, MovieStorage> {
    override fun map(from: MovieData) = from.run {
        MovieStorage(
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