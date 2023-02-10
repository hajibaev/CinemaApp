package com.example.data.mappers.movie

import com.example.data.models.movie.SeriesData
import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain

class MapSeriesDomainToData : Maps<SeriesDomain, SeriesData> {
    override fun map(from: SeriesDomain) = from.run {
        SeriesData(
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