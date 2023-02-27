package com.example.data.data.mappers.movie

import com.example.data.data.models.person.CastData
import com.example.data.data.models.person.CreditsResponseData
import com.example.data.data.models.person.CrewData
import com.example.domain.base.Mapper
import com.example.domain.models.person.CastDomain
import com.example.domain.models.person.CreditsResponseDomain
import com.example.domain.models.person.CrewDomain
import javax.inject.Inject

class MapCreditsResponseDataToDomain @Inject constructor(
    private val mapListCastDataToDomain: Mapper<List<CastData>, List<CastDomain>>,
    private val mapListCrewDataToDomain: Mapper<List<CrewData>, List<CrewDomain>>,
) : Mapper<CreditsResponseData, CreditsResponseDomain> {
    override fun map(from: CreditsResponseData) = from.run {
        CreditsResponseDomain(
            cast = mapListCastDataToDomain.map(cast),
            crew = mapListCrewDataToDomain.map(crew),
            id = id
        )
    }
}