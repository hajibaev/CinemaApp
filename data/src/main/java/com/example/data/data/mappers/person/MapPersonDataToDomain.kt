package com.example.data.data.mappers.person

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.person.PersonData
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDomain
import javax.inject.Inject

class MapPersonDataToDomain @Inject constructor(private val mapper: Mapper<List<MovieData>, List<MovieDomain>>) :
    Mapper<PersonData, PersonDomain> {
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
