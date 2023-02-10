package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.GenresCloud
import com.example.data.cloud.models.movie.TvSeriesDetailsCloud
import com.example.data.models.movie.GenresData
import com.example.data.models.movie.TvSeriesDetailsData
import com.example.domain.Maps

class MapTvDetailsCloudToData(private val mapper: Maps<List<GenresCloud>, List<GenresData>>) :
    Maps<TvSeriesDetailsCloud, TvSeriesDetailsData> {
    override fun map(from: TvSeriesDetailsCloud) = from.run {
        TvSeriesDetailsData(
            adult = adult,
            backdropPath = backdropPath,
            episodeRunTime = episodeRunTime.map { episodeRunTime -> episodeRunTime },
            firstAirDate = firstAirDate,
            genres = mapper.map(genres),
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