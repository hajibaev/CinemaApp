package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.mymovieapp.app.models.movie.MovieUi
import javax.inject.Inject

class MapMovieUiToDomain @Inject constructor() : Mapper<MovieUi, MovieDomain> {
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
            minimumAge = minimumAge
        )
    }
}