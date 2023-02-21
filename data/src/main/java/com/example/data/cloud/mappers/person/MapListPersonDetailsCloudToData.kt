package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.data.models.person.PersonDetailsData
import com.example.domain.base.Mapper

class MapListPersonDetailsCloudToData (private val mapper: Mapper<PersonDetailsCloud, PersonDetailsData>) :
    Mapper<List<PersonDetailsCloud>, List<PersonDetailsData>> {
    override fun map(from: List<PersonDetailsCloud>) = from.map {
        mapper.map(it)
    }
}