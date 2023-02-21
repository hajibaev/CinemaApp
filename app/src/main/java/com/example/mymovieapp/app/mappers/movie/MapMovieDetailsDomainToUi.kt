package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.app.models.movie.MovieDetailsUi
import javax.inject.Inject

class MapMovieDetailsDomainToUi @Inject constructor() :
    Mapper<MovieDetailsDomain, MovieDetailsUi> {
    override fun map(from: MovieDetailsDomain) = from.run {
        MovieDetailsUi(
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