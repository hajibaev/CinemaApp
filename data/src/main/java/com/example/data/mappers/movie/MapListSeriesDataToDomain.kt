package com.example.data.mappers.movie

import com.example.data.models.movie.SeriesData
import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain

class MapListSeriesDataToDomain(private val mapper: Maps<SeriesData, SeriesDomain>) :
    Maps<List<SeriesData>, List<SeriesDomain>> {
    override fun map(from: List<SeriesData>) = from.map {
        mapper.map(it)
    }
}