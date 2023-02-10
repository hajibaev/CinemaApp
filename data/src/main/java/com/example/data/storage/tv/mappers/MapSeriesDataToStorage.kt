package com.example.data.storage.tv.mappers

import com.example.data.models.movie.SeriesData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.Maps

class MapSeriesDataToStorage : Maps<SeriesData, TvStorage> {
    override fun map(from: SeriesData) = from.run {
        TvStorage(
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