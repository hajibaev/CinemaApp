package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain
import com.example.mymovieapp.models.movie.SeriesUi

class MapSeriesDomainToUi: Maps<SeriesDomain, SeriesUi> {
    override fun map(from: SeriesDomain) = from.run {
        SeriesUi(
            backdropPath = backdropPath,
            firstAirDate = firstAirDate,
            genreIds = genreIds.map { ids -> ids },
            id = id,
            name = name,
            originalLanguage = originalLanguage,
            originalName = originalName,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }
}