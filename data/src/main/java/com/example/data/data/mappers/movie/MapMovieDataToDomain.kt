package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.MovieData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import javax.inject.Inject

class MapMovieDataToDomain @Inject constructor() : Mapper<MovieData, MovieDomain> {
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