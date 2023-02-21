package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.SeriesData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import javax.inject.Inject

class MapSeriesDataToDomain @Inject constructor() : Mapper<SeriesData, SeriesDomain> {
    override fun map(from: SeriesData) = from.run {
        SeriesDomain(
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