package com.example.mymovieapp.app.mappers.person

import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDomain
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.person.PersonPresentation
import javax.inject.Inject

class MapPersonDomainToUi @Inject constructor(private val mapper: Mapper<List<MovieDomain>, List<MovieUi>>) :
    Mapper<PersonDomain, PersonPresentation> {
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
