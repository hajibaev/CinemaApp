package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.models.person.PersonDetailsData
import com.example.domain.Maps

class MapPersonDetailsCloudToData : Maps<PersonDetailsCloud, PersonDetailsData> {
    override fun map(from: PersonDetailsCloud) = from.run {
        PersonDetailsData(
            known_for_department = known_for_department,
            also_known_as = also_known_as.map { it },
            biography = biography,
            birthday = birthday,
            deathDay = deathDay,
            gender = if (gender == 1) "Female" else "Male",
            id = id,
            name = name,
            popularity = popularity,
            place_of_birth = place_of_birth,
            profile_path = profile_path
        )
    }
}