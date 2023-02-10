package com.example.data.mappers.movie

import com.example.data.models.movie.SeriesData
import com.example.data.models.movie.TvSeriesResponseData
import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesResponseDomain

class MapTvResponseDataToDomain(private val mapper: Maps<List<SeriesData>, List<SeriesDomain>>) :
    Maps<TvSeriesResponseData, TvSeriesResponseDomain> {
    override fun map(from: TvSeriesResponseData) = from.run {
        TvSeriesResponseDomain(
            page = page,
            results = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results
        )
    }
}