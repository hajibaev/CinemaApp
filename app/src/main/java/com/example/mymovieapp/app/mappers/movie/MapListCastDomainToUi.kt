package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.person.CastDomain
import com.example.mymovieapp.app.models.person.CastUi

class MapListCastDomainToUi (private val mapper: Mapper<CastDomain, CastUi>) :
    Mapper<List<CastDomain>, List<CastUi>> {
    override fun map(from: List<CastDomain>) = from.map {
        mapper.map(it)
    }
}