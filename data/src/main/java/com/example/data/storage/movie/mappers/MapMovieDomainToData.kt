package com.example.data.storage.movie.mappers

import com.example.data.models.movie.MovieData
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain

class MapMovieDomainToData : Maps<MovieDomain, MovieData> {
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