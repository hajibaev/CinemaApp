package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.MovieDetailsData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDetailsDomain
import javax.inject.Inject

class MapMovieDetailsDataToDomain @Inject constructor() :
    Mapper<MovieDetailsData, MovieDetailsDomain> {
    override fun map(from: MovieDetailsData) = from.run {
        MovieDetailsDomain(
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