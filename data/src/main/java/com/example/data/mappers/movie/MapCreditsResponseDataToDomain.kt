package com.example.data.mappers.movie

import com.example.data.models.movie.CastData
import com.example.data.models.movie.CreditsResponseData
import com.example.domain.Maps
import com.example.domain.models.movie.CastDomain
import com.example.domain.models.movie.CreditsResponseDomain

class MapCreditsResponseDataToDomain(
    private val mapListCastDataToDomain: Maps<List<CastData>, List<CastDomain>>,
) : Maps<CreditsResponseData, CreditsResponseDomain> {
    override fun map(from: CreditsResponseData) = from.run {
        CreditsResponseDomain(
            cast = mapListCastDataToDomain.map(cast),
            id = id
        )
    }
}