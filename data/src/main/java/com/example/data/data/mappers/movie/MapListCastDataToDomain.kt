package com.example.data.data.mappers.movie

import com.example.data.data.models.person.CastData
import com.example.domain.base.Mapper
import com.example.domain.models.person.CastDomain

class MapListCastDataToDomain (private val mapper: Mapper<CastData, CastDomain>) :
    Mapper<List<CastData>, List<CastDomain>> {
    override fun map(from: List<CastData>) = from.map {
        mapper.map(it)
    }
}