package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.mymovieapp.models.movie.SeriesUi
import com.example.mymovieapp.models.movie.TvSeriesResponseUi

class MapTvResponseDomainToUi(private val mapper: Maps<List<SeriesDomain>, List<SeriesUi>>) :
    Maps<TvSeriesResponseDomain, TvSeriesResponseUi> {
    override fun map(from: TvSeriesResponseDomain) = from.run {
        TvSeriesResponseUi(
            page = page,
            results = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results
        )
    }
}