package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.models.person.PersonDetailsData
import com.example.domain.Maps

class MapListPersonDetailsCloudToData(private val mapper: Maps<PersonDetailsCloud, PersonDetailsData>) :
    Maps<List<PersonDetailsCloud>, List<PersonDetailsData>> {
    override fun map(from: List<PersonDetailsCloud>) = from.map {
        mapper.map(it)
    }
}