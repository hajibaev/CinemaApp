package com.example.data.data.mappers.person

import com.example.data.data.models.person.PersonDetailsData
import com.example.domain.base.Mapper
import com.example.domain.models.person.PersonDetailsDomain
import javax.inject.Inject

class MapPersonDetailsDataToDomain @Inject constructor():
    Mapper<PersonDetailsData, PersonDetailsDomain> {
    override fun map(from: PersonDetailsData) = from.run {
        PersonDetailsDomain(
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