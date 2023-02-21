package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.TvSeriesDetailsDomain
import com.example.mymovieapp.app.models.movie.TvSeriesDetailsUi
import javax.inject.Inject

class MapTvSeriesDetailsDomainToUi @Inject constructor() :
    Mapper<TvSeriesDetailsDomain, TvSeriesDetailsUi> {
    override fun map(from: TvSeriesDetailsDomain) = from.run {
        TvSeriesDetailsUi(
            adult = adult,
            backdropPath = backdropPath,
            episodeRunTime = episodeRunTime.map { episodeRunTime -> episodeRunTime },
            firstAirDate = firstAirDate,
            homepage = homepage,
            id = id,
            inProduction = inProduction,
            languages = languages.map { languages -> languages },
            lastAirDate = lastAirDate,
            name = name,
            numberOfEpisodes = numberOfEpisodes,
            numberOfSeasons = numberOfSeasons,
            originCountry = originCountry.map { originCountry -> originCountry },
            originalLanguage = originalLanguage,
            originalName = originalName,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            status = status,
            tagline = tagline,
            type = type,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}