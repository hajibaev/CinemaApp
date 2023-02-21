package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.movie.TvSeriesResponseDomain
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.models.movie.TvSeriesResponseUi
import javax.inject.Inject

class MapTvResponseDomainToUi @Inject constructor(private val mapper: Mapper<List<SeriesDomain>, List<SeriesUi>>) :
    Mapper<TvSeriesResponseDomain, TvSeriesResponseUi> {
    override fun map(from: TvSeriesResponseDomain) = from.run {
        TvSeriesResponseUi(
            page = page,
            results = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results
        )
    }
}