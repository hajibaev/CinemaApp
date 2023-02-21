package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.CastDomain
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.mymovieapp.app.models.movie.CastUi
import com.example.mymovieapp.app.models.movie.CreditsResponseUi
import javax.inject.Inject

class MapCreditsResponseDomainToUi @Inject constructor(
    private val mapListCastDomainToUi: Mapper<List<CastDomain>, List<CastUi>>,
) : Mapper<CreditsResponseDomain, CreditsResponseUi> {
    override fun map(from: CreditsResponseDomain) = from.run {
        CreditsResponseUi(
            cast = mapListCastDomainToUi.map(cast),
            id = id
        )
    }
}