package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.GenresDomain
import com.example.domain.models.movie.TvSeriesDetailsDomain
import com.example.mymovieapp.models.movie.MovieGenresPresentation
import com.example.mymovieapp.models.movie.TvSeriesDetailsUi

class MapTvSeriesDetailsDomainToUi(private val mapper: Maps<List<GenresDomain>, List<MovieGenresPresentation>>) :
    Maps<TvSeriesDetailsDomain, TvSeriesDetailsUi> {
    override fun map(from: TvSeriesDetailsDomain) = from.run {
        TvSeriesDetailsUi(
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