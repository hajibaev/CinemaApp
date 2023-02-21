package com.example.mymovieapp.app.mappers.person

import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDomain
import com.example.mymovieapp.app.models.person.PersonPresentation

class MapListPersonDomainToUi (private val mapper: Mapper<PersonDomain, PersonPresentation>) :
    Mapper<List<PersonDomain>, List<PersonPresentation>> {
    override fun map(from: List<PersonDomain>) = from.run {
        map { person ->
            mapper.map(person)
        }
    }
}