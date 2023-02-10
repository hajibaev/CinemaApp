package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.GenresDomain
import com.example.mymovieapp.models.movie.MovieDetailsUi
import com.example.mymovieapp.models.movie.MovieGenresPresentation

class MapMovieDetailsDomainToUi(private val mapper: Maps<List<GenresDomain>, List<MovieGenresPresentation>>) :
    Maps<MovieDetailsDomain, MovieDetailsUi> {
    override fun map(from: MovieDetailsDomain) = from.run {
        MovieDetailsUi(
            backdrop_path = backdrop_path,
            budget = budget,
            actors = mapper.map(actors),
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