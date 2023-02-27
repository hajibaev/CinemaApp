package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.CrewCloud
import com.example.data.data.models.person.CrewData
import com.example.domain.base.Mapper

class MapListCrewCloudToData(private val mapper: Mapper<CrewCloud, CrewData>) :
    Mapper<List<CrewCloud>, List<CrewData>> {
    override fun map(from: List<CrewCloud>) = from.map {
        mapper.map(it)
    }
}