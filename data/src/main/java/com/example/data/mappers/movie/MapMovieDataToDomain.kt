package com.example.data.mappers.movie

import com.example.data.models.movie.MovieData
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain

class MapMovieDataToDomain : Maps<MovieData, MovieDomain> {
    override fun map(from: MovieData) = from.run {
        MovieDomain(
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