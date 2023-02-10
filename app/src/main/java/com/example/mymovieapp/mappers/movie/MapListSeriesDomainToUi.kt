package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.SeriesDomain
import com.example.mymovieapp.models.movie.SeriesUi

class MapListSeriesDomainToUi(private val mapper: Maps<SeriesDomain, SeriesUi>) :
    Maps<List<SeriesDomain>, List<SeriesUi>> {
    override fun map(from: List<SeriesDomain>) = from.map {
        mapper.map(it)
    }
}