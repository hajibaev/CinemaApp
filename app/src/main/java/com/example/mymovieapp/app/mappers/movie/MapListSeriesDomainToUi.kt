package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.SeriesDomain
import com.example.mymovieapp.app.models.movie.SeriesUi

class MapListSeriesDomainToUi (private val mapper: Mapper<SeriesDomain, SeriesUi>) :
    Mapper<List<SeriesDomain>, List<SeriesUi>> {
    override fun map(from: List<SeriesDomain>) = from.map {
        mapper.map(it)
    }
}