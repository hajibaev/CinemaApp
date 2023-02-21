package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import com.example.mymovieapp.app.models.movie.SeriesUi
import javax.inject.Inject

class MapSeriesUiToDomain @Inject constructor() : Mapper<SeriesUi, SeriesDomain> {
    override fun map(from: SeriesUi) = from.run {
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