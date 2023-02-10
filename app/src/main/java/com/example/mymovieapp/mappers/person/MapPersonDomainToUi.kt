package com.example.mymovieapp.mappers.person

import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDomain
import com.example.mymovieapp.models.movie.MovieUi
import com.example.mymovieapp.models.person.PersonPresentation

class MapPersonDomainToUi(private val mapper: Maps<List<MovieDomain>, List<MovieUi>>) :
    Maps<PersonDomain, PersonPresentation> {
    override fun map(from: PersonDomain) = from.run {
        PersonPresentation(
            profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapper.map(known_for),
            name = name,
            popularity = popularity
        )
    }
}
