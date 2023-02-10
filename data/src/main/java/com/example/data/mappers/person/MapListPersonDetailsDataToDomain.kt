package com.example.data.mappers.person

import com.example.data.models.person.PersonDetailsData
import com.example.domain.Maps
import com.example.domain.models.person.PersonDetailsDomain

class MapListPersonDetailsDataToDomain(private val mapper: Maps<PersonDetailsData, PersonDetailsDomain>) :
    Maps<List<PersonDetailsData>, List<PersonDetailsDomain>> {
    override fun map(from: List<PersonDetailsData>) = from.map {
        mapper.map(it)
    }
}