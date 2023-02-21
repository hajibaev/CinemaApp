package com.example.data.data.mappers.person

import com.example.data.data.models.person.PersonData
import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDomain

class MapListPersonDataToDomain (private val mapper: Mapper<PersonData, PersonDomain>) :
    Mapper<List<PersonData>, List<PersonDomain>> {
    override fun map(from: List<PersonData>) = from.map {
        mapper.map(it)
    }
}