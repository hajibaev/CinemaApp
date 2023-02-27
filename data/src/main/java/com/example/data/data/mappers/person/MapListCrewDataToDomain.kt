package com.example.data.data.mappers.person

import com.example.data.data.models.person.CrewData
import com.example.domain.base.Mapper
import com.example.domain.models.person.CrewDomain

class MapListCrewDataToDomain(private val mapper: Mapper<CrewData, CrewDomain>) :
    Mapper<List<CrewData>, List<CrewDomain>> {
    override fun map(from: List<CrewData>) = from.map {
        mapper.map(it)
    }
}