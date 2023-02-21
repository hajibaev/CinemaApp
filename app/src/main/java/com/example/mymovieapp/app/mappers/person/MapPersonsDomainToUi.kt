package com.example.mymovieapp.app.mappers.person

import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDomain
import com.example.domain.models.person.PersonsDomain
import com.example.mymovieapp.app.models.person.PersonPresentation
import com.example.mymovieapp.app.models.person.PersonsPresentation
import javax.inject.Inject

class MapPersonsDomainToUi @Inject constructor(private val mapper: Mapper<List<PersonDomain>, List<PersonPresentation>>) :
    Mapper<PersonsDomain, PersonsPresentation> {
    override fun map(from: PersonsDomain) = from.run {
        PersonsPresentation(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}