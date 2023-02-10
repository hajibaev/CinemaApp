package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.SeriesCloud
import com.example.data.models.movie.SeriesData
import com.example.domain.Maps

class MapSeriesCloudToData : Maps<SeriesCloud, SeriesData> {
    override fun map(from: SeriesCloud) = from.run {
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