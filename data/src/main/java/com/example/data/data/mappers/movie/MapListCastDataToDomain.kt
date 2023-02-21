package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.CastData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.CastDomain

class MapListCastDataToDomain (private val mapper: Mapper<CastData, CastDomain>) :
    Mapper<List<CastData>, List<CastDomain>> {
    override fun map(from: List<CastData>) = from.map {
        mapper.map(it)
    }
}