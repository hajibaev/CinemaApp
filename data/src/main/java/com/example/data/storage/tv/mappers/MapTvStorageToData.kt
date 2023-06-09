package com.example.data.storage.tv.mappers

import com.example.data.data.models.movie.SeriesData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapTvStorageToDataMaps @Inject constructor(): Mapper<TvStorage, SeriesData> {
    override fun map(from: TvStorage) = from.run {
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