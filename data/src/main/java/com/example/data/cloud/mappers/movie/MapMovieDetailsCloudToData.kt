package com.example.data.cloud.mappers.movie
import com.example.data.cloud.models.movie.MovieDetailsCloud
import com.example.data.cloud.models.movie.GenresCloud
import com.example.data.models.movie.MovieDetailsData
import com.example.data.models.movie.GenresData
import com.example.domain.Maps

class MapMovieDetailsCloudToData(private val mapper: Maps<List<GenresCloud>, List<GenresData>>) :
        Maps<MovieDetailsCloud, MovieDetailsData> {
        override fun map(from: MovieDetailsCloud) = from.run {
            MovieDetailsData(
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