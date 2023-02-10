package com.example.mymovieapp.mappers.person

import com.example.domain.Maps
import com.example.domain.models.person.PersonDomain
import com.example.mymovieapp.models.person.PersonPresentation

class MapListPersonDomainToUi(private val mapper: Maps<PersonDomain, PersonPresentation>) :
    Maps<List<PersonDomain>, List<PersonPresentation>> {
    override fun map(from: List<PersonDomain>) = from.run {
        map { person ->
            mapper.map(person)
        }
    }
}