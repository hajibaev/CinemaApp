package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.SeriesCloud
import com.example.data.models.movie.SeriesData
import com.example.domain.Maps

class MapListSeriesCloudToData(private val mapper: Maps<SeriesCloud, SeriesData>) :
    Maps<List<SeriesCloud>, List<SeriesData>> {
    override fun map(from: List<SeriesCloud>) = from.map {
        mapper.map(it)
    }
}