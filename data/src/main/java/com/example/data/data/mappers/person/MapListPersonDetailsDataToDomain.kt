package com.example.data.data.mappers.person

import com.example.data.data.models.person.PersonDetailsData
import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDetailsDomain

class MapListPersonDetailsDataToDomain (private val mapper: Mapper<PersonDetailsData, PersonDetailsDomain>) :
    Mapper<List<PersonDetailsData>, List<PersonDetailsDomain>> {
    override fun map(from: List<PersonDetailsData>) = from.map {
        mapper.map(it)
    }
}