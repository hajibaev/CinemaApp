package com.example.mymovieapp.app.mappers.person

import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.app.models.person.PersonDetailsPresentation

class MapListPersonDetailsDomainToUi(private val mapper: Mapper<PersonDetailsDomain, PersonDetailsPresentation>) :
    Mapper<List<PersonDetailsDomain>, List<PersonDetailsPresentation>> {
    override fun map(from: List<PersonDetailsDomain>) = from.map {
        mapper.map(it)
    }
}