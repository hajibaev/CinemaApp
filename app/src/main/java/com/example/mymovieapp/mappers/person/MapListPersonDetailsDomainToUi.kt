package com.example.mymovieapp.mappers.person

import com.example.domain.Maps
import com.example.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.models.person.PersonDetailsPresentation

class MapListPersonDetailsDomainToUi(private val mapper: Maps<PersonDetailsDomain, PersonDetailsPresentation>) :
    Maps<List<PersonDetailsDomain>, List<PersonDetailsPresentation>> {
    override fun map(from: List<PersonDetailsDomain>) = from.map {
        mapper.map(it)
    }
}