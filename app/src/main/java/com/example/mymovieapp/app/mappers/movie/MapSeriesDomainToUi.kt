package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import com.example.mymovieapp.app.models.movie.SeriesUi
import javax.inject.Inject

class MapSeriesDomainToUi @Inject constructor(): Mapper<SeriesDomain, SeriesUi> {
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