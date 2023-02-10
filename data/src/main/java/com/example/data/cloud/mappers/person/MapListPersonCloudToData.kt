package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonCloud
import com.example.data.models.person.PersonData
import com.example.domain.Maps

class MapListPersonCloudToData(private val mapper: Maps<PersonCloud, PersonData>) :
    Maps<List<PersonCloud>, List<PersonData>> {
    override fun map(from: List<PersonCloud>) = from.map {
        mapper.map(it)
    }
}