package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.SeriesData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain

class MapListSeriesDataToDomain (private val mapper: Mapper<SeriesData, SeriesDomain>) :
    Mapper<List<SeriesData>, List<SeriesDomain>> {
    override fun map(from: List<SeriesData>) = from.map {
        mapper.map(it)
    }
}