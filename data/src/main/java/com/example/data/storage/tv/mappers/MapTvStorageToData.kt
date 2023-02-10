package com.example.data.storage.tv.mappers

import com.example.data.models.movie.SeriesData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.Maps

class MapTvStorageToDataMaps : Maps<TvStorage, SeriesData> {
    override fun map(from: TvStorage) = from.run {
        SeriesData(
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