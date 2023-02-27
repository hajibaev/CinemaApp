package com.example.data.data.mappers.person

import com.example.data.data.models.person.CrewData
import com.example.domain.base.Mapper
import com.example.domain.models.person.CrewDomain
import javax.inject.Inject

class MapCrewDataToDomain @Inject constructor() : Mapper<CrewData, CrewDomain> {
    override fun map(from: CrewData) = from.run {
        CrewDomain(
            profile_path = profile_path,
            id = id,
            known_for_department = known_for_department,
            popularity = popularity,
            original_name = original_name
        )
    }
}