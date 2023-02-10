package com.example.data.mappers.movie

import com.example.data.models.movie.SeriesData
import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain

class MapSeriesDataToDomain : Maps<SeriesData, SeriesDomain> {
    override fun map(from: SeriesData) = from.run {
        SeriesDomain(
            backdropPath = backdropPath,
            firstAirDate = firstAirDate,
            genreIds = genreIds.map { ids -> ids },
            id = id,
            name = name,
//            originCountry = originCountry.map { country -> country },
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