package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.CastDomain
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.MovieDomain
import com.example.mymovieapp.app.models.movie.CastUi
import com.example.mymovieapp.app.models.movie.CreditsResponseUi
import com.example.mymovieapp.app.models.movie.MovieUi
import javax.inject.Inject

class MapCreditsResponseDomainToUi @Inject constructor(
    private val mapListCastDomainToUi: Mapper<List<CastDomain>, List<CastUi>>,
//    private val mapListMovieDomainToUi: Mapper<List<MovieDomain>, List<MovieUi>>,
) : Mapper<CreditsResponseDomain, CreditsResponseUi> {
    override fun map(from: CreditsResponseDomain) = from.run {
        CreditsResponseUi(
            cast = mapListCastDomainToUi.map(cast),
//            crew = mapListMovieDomainToUi.map(crew),
            id = id
        )
    }
}