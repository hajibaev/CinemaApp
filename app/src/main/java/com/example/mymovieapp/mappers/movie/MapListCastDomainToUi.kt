package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.CastDomain
import com.example.mymovieapp.models.movie.CastUi

class MapListCastDomainToUi(private val mapper: Maps<CastDomain, CastUi>) :
    Maps<List<CastDomain>, List<CastUi>> {
    override fun map(from: List<CastDomain>) = from.map {
        mapper.map(it)
    }
}