package com.example.data.mappers.person

import com.example.data.models.person.PersonData
import com.example.data.models.person.PersonsData
import com.example.domain.Maps
import com.example.domain.models.person.PersonDomain
import com.example.domain.models.person.PersonsDomain

class MapPersonsDataToDomain(private val mapper: Maps<List<PersonData>, List<PersonDomain>>) :
    Maps<PersonsData, PersonsDomain> {
    override fun map(from: PersonsData) = from.run {
        PersonsDomain(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}