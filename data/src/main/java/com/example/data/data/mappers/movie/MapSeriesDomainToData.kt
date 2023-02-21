package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.SeriesData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import javax.inject.Inject

class MapSeriesDomainToData @Inject constructor() : Mapper<SeriesDomain, SeriesData> {
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