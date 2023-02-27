package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.person.CastDomain
import com.example.domain.models.person.CreditsResponseDomain
import com.example.domain.models.person.CrewDomain
import com.example.mymovieapp.app.models.person.CastUi
import com.example.mymovieapp.app.models.person.CreditsResponseUi
import com.example.mymovieapp.app.models.person.CrewUi
import javax.inject.Inject

class MapCreditsResponseDomainToUi @Inject constructor(
    private val mapListCastDomainToUi: Mapper<List<CastDomain>, List<CastUi>>,
    private val mapListCrewDomainToUi: Mapper<List<CrewDomain>, List<CrewUi>>,
) : Mapper<CreditsResponseDomain, CreditsResponseUi> {
    override fun map(from: CreditsResponseDomain) = from.run {
        CreditsResponseUi(
            cast = mapListCastDomainToUi.map(cast),
            crew = mapListCrewDomainToUi.map(crew),
            id = id
        )
    }
}