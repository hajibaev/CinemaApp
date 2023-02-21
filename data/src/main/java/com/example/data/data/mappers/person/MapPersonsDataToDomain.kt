package com.example.data.data.mappers.person

import com.example.data.data.models.person.PersonData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDomain
import com.example.domain.models.person.PersonsDomain
import javax.inject.Inject

class MapPersonsDataToDomain @Inject constructor(private val mapper: Mapper<List<PersonData>, List<PersonDomain>>) :
    Mapper<PersonsData, PersonsDomain> {
    override fun map(from: PersonsData) = from.run {
        PersonsDomain(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}