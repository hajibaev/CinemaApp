package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonCloud
import com.example.data.data.models.person.PersonData
import com.example.domain.base.Mapper

class MapListPersonCloudToData(private val mapper: Mapper<PersonCloud, PersonData>) :
    Mapper<List<PersonCloud>, List<PersonData>> {
    override fun map(from: List<PersonCloud>) = from.map {
        mapper.map(it)
    }
}