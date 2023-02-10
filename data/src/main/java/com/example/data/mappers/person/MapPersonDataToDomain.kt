package com.example.data.mappers.person

import com.example.data.models.movie.MovieData
import com.example.data.models.person.PersonData
import com.example.domain.Maps
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDomain

class MapPersonDataToDomain(private val mapper: Maps<List<MovieData>, List<MovieDomain>>) :
    Maps<PersonData, PersonDomain> {
    override fun map(from: PersonData) = from.run {
        PersonDomain(
            profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapper.map(known_for),
            name = name,
            popularity = popularity
        )
    }
}
