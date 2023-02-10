package com.example.data.mappers.movie

import com.example.data.models.movie.MovieDetailsData
import com.example.data.models.movie.GenresData
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.GenresDomain

class MapMovieDetailsDataToDomain(private val mapper: Maps<List<GenresData>, List<GenresDomain>>) :
    Maps<MovieDetailsData, MovieDetailsDomain> {
    override fun map(from: MovieDetailsData) = from.run {
        MovieDetailsDomain(
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