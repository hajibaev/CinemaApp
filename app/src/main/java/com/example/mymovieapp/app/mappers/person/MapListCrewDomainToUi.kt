package com.example.mymovieapp.app.mappers.person

import com.example.domain.base.Mapper
import com.example.domain.models.person.CrewDomain
import com.example.mymovieapp.app.models.person.CrewUi

class MapListCrewDomainToUi(private val mapper: Mapper<CrewDomain, CrewUi>) :
    Mapper<List<CrewDomain>, List<CrewUi>> {
    override fun map(from: List<CrewDomain>) = from.map {
        mapper.map(it)
    }
}