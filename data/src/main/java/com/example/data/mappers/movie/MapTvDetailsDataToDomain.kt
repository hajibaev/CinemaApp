package com.example.data.mappers.movie

import com.example.data.models.movie.GenresData
import com.example.data.models.movie.TvSeriesDetailsData
import com.example.domain.Maps
import com.example.domain.models.movie.GenresDomain
import com.example.domain.models.movie.TvSeriesDetailsDomain

class MapTvDetailsDataToDomain(private val mapper: Maps<List<GenresData>, List<GenresDomain>>) :
    Maps<TvSeriesDetailsData, TvSeriesDetailsDomain> {
    override fun map(from: TvSeriesDetailsData) = from.run {
        TvSeriesDetailsDomain(
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