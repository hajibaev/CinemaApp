package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.cloud.models.person.PersonCloud
import com.example.data.models.movie.MovieData
import com.example.data.models.person.PersonData
import com.example.domain.Maps

class MapPersonCloudToData(private val mapper: Maps<List<MovieCloud>, List<MovieData>>) :
    Maps<PersonCloud, PersonData> {
    override fun map(from: PersonCloud) = from.run {
        PersonData(
            profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapper.map(known_for),
            name = name,
            popularity = popularity
        )
    }
}
