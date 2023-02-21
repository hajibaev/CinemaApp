package com.example.data.storage.movie.mappers

import com.example.data.data.models.movie.MovieData
import com.example.data.storage.movie.MovieStorage
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapMovieStorageToData @Inject constructor() : Mapper<MovieStorage, MovieData> {
    override fun map(from: MovieStorage) = from.run {
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