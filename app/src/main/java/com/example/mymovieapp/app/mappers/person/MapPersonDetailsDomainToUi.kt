package com.example.mymovieapp.app.mappers.person

import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.app.models.person.PersonDetailsPresentation
import javax.inject.Inject

class MapPersonDetailsDomainToUi @Inject constructor() :
    Mapper<PersonDetailsDomain, PersonDetailsPresentation> {
    override fun map(from: PersonDetailsDomain) = from.run {
        PersonDetailsPresentation(
            known_for_department = known_for_department,
            also_known_as = also_known_as.map { it },
            biography = biography,
            birthday = birthday,
            deathDay = deathDay,
            gender = gender,
            id = id,
            name = name,
            popularity = popularity,
            place_of_birth = place_of_birth,
            profile_path = profile_path
        )
    }
}