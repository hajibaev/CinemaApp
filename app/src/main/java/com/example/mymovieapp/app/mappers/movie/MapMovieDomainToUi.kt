package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.mymovieapp.app.models.movie.MovieUi
import javax.inject.Inject

class MapMovieDomainToUi @Inject constructor(): Mapper<MovieDomain, MovieUi> {
    override fun map(from: MovieDomain) = from.run {
        MovieUi(
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