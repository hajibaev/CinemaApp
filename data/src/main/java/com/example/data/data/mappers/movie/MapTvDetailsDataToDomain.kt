package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.TvSeriesDetailsData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.TvSeriesDetailsDomain
import javax.inject.Inject

class MapTvDetailsDataToDomain @Inject constructor() :
    Mapper<TvSeriesDetailsData, TvSeriesDetailsDomain> {
    override fun map(from: TvSeriesDetailsData) = from.run {
        TvSeriesDetailsDomain(
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