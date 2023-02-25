package com.example.data.data.mappers.movie

import com.example.data.data.models.movie.CastData
import com.example.data.data.models.movie.CreditsResponseData
import com.example.data.data.models.movie.MovieData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.CastDomain
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.MovieDomain
import javax.inject.Inject

class MapCreditsResponseDataToDomain @Inject constructor(
    private val mapListCastDataToDomain: Mapper<List<CastData>, List<CastDomain>>,
//    private val mapListMovieDataToDomain: Mapper<List<MovieData>, List<MovieDomain>>,
) : Mapper<CreditsResponseData, CreditsResponseDomain> {
    override fun map(from: CreditsResponseData) = from.run {
        CreditsResponseDomain(
            cast = mapListCastDataToDomain.map(cast),
//            crew = mapListMovieDataToDomain.map(crew),
            id = id
        )
    }
}