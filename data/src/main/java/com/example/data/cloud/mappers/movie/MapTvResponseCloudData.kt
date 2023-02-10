package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.SeriesCloud
import com.example.data.cloud.models.movie.TvSeriesResponseCloud
import com.example.data.models.movie.SeriesData
import com.example.data.models.movie.TvSeriesResponseData
import com.example.domain.Maps

class MapTvResponseCloudData(private val mapper: Maps<List<SeriesCloud>, List<SeriesData>>) :
    Maps<TvSeriesResponseCloud, TvSeriesResponseData> {
    override fun map(from: TvSeriesResponseCloud) = from.run {
        TvSeriesResponseData(
            page = page,
            results = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results
        )
    }
}