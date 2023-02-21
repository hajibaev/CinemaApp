package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.CastData
import com.example.data.data.models.movie.CreditsResponseData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.CastDomain
import com.example.domain.models.movie.CreditsResponseDomain
import javax.inject.Inject

class MapCreditsResponseDataToDomain @Inject constructor(
    private val mapListCastDataToDomain: Mapper<List<CastData>, List<CastDomain>>,
) : Mapper<CreditsResponseData, CreditsResponseDomain> {
    override fun map(from: CreditsResponseData) = from.run {
        CreditsResponseDomain(
            cast = mapListCastDataToDomain.map(cast),
            id = id
        )
    }
}