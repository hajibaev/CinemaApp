package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.SeriesCloud
import com.example.data.data.models.movie.SeriesData
import com.example.domain.base.Mapper

class MapListSeriesCloudToData (private val mapper: Mapper<SeriesCloud, SeriesData>) :
    Mapper<List<SeriesCloud>, List<SeriesData>> {
    override fun map(from: List<SeriesCloud>) = from.map {
        mapper.map(it)
    }
}