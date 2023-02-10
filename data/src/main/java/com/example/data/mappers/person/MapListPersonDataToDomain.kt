package com.example.data.mappers.person

import com.example.data.models.person.PersonData
import com.example.domain.Maps
import com.example.domain.models.person.PersonDomain

class MapListPersonDataToDomain(private val mapper: Maps<PersonData, PersonDomain>) :
    Maps<List<PersonData>, List<PersonDomain>> {
    override fun map(from: List<PersonData>) = from.map {
            mapper.map(it)
    }
}