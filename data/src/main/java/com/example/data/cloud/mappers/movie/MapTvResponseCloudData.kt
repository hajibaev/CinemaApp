package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.SeriesCloud
import com.example.data.cloud.models.movie.TvSeriesResponseCloud
import com.example.data.data.models.movie.SeriesData
import com.example.data.data.models.movie.TvSeriesResponseData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapTvResponseCloudData @Inject constructor(private val mapper: Mapper<List<SeriesCloud>, List<SeriesData>>) :
    Mapper<TvSeriesResponseCloud, TvSeriesResponseData> {
    override fun map(from: TvSeriesResponseCloud) = from.run {
        TvSeriesResponseData(
            page = page,
            results = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results
        )
    }
}