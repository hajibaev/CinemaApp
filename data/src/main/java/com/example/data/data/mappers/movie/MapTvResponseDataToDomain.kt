package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.SeriesData
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import javax.inject.Inject

class MapTvResponseDataToDomain @Inject constructor(private val mapper: Mapper<List<SeriesData>, List<SeriesDomain>>) :
    Mapper<TvSeriesResponseData, TvSeriesResponseDomain> {
    override fun map(from: TvSeriesResponseData) = from.run {
        TvSeriesResponseDomain(
            page = page,
            results = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results
        )
    }
}