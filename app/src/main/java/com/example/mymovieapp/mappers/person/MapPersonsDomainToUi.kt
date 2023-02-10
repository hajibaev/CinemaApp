package com.example.mymovieapp.mappers.person

import com.example.domain.Maps
import com.example.domain.models.person.PersonDomain
import com.example.domain.models.person.PersonsDomain
import com.example.mymovieapp.models.person.PersonPresentation
import com.example.mymovieapp.models.person.PersonsPresentation

class MapPersonsDomainToUi(private val mapper: Maps<List<PersonDomain>, List<PersonPresentation>>) :
    Maps<PersonsDomain, PersonsPresentation> {
    override fun map(from: PersonsDomain) = from.run {
        PersonsPresentation(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}