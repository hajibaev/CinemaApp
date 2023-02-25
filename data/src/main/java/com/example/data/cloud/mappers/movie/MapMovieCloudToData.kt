package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.data.models.movie.MovieData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapMovieCloudToData @Inject constructor() : Mapper<MovieCloud, MovieData> {
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
            minimumAge = minimumAge
        )
    }
}