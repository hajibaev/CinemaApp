package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieDetailsCloud
import com.example.data.data.models.movie.MovieDetailsData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapMovieDetailsCloudToData @Inject constructor() :
    Mapper<MovieDetailsCloud, MovieDetailsData> {
    override fun map(from: MovieDetailsCloud) = from.run {
        MovieDetailsData(
            backdrop_path = backdrop_path,
            budget = budget,
            movieId = movieId,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            status = status,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}