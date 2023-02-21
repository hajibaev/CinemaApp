package com.example.data.storage.movie.mappers

import com.example.data.data.models.movie.MovieData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import javax.inject.Inject

class MapMovieDomainToData @Inject constructor(): Mapper<MovieDomain, MovieData> {
    override fun map(from: MovieDomain) = from.run {
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