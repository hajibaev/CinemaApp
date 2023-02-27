package com.example.mymovieapp.app.mappers.person

import com.example.domain.base.Mapper
import com.example.domain.models.person.CrewDomain
import com.example.mymovieapp.app.models.person.CrewUi
import javax.inject.Inject

class MapCrewDomainToUi @Inject constructor() : Mapper<CrewDomain, CrewUi> {
    override fun map(from: CrewDomain) = from.run {
        CrewUi(
            profile_path = profile_path,
            id = id,
            known_for_department = known_for_department,
            popularity = popularity,
            original_name = original_name
        )
    }
}