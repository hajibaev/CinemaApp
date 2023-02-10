package com.example.data.mappers.movie

import com.example.data.models.movie.CastData
import com.example.domain.Maps
import com.example.domain.models.movie.CastDomain

class MapListCastDataToDomain(private val mapper: Maps<CastData, CastDomain>) :
    Maps<List<CastData>, List<CastDomain>> {
    override fun map(from: List<CastData>) = from.map {
        mapper.map(it)
    }
}