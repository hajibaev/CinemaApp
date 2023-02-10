package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.CastDomain
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.mymovieapp.models.movie.CastUi
import com.example.mymovieapp.models.movie.CreditsResponseUi

class MapCreditsResponseDomainToUi(
    private val mapListCastDomainToUi: Maps<List<CastDomain>, List<CastUi>>,
    ) : Maps<CreditsResponseDomain, CreditsResponseUi> {
    override fun map(from: CreditsResponseDomain) = from.run {
        CreditsResponseUi(
            cast = mapListCastDomainToUi.map(cast),
            id = id
        )
    }
}