package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.mymovieapp.models.movie.MovieUi

class MapMovieUiToDomain : Maps<MovieUi, MovieDomain> {
    override fun map(from: MovieUi) = from.run {
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