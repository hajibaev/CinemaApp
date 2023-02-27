package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.CrewCloud
import com.example.data.data.models.person.CrewData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapCrewCloudToData @Inject constructor() : Mapper<CrewCloud, CrewData> {
    override fun map(from: CrewCloud) = from.run {
        CrewData(
            profile_path = profile_path,
            id = id,
            known_for_department = known_for_department,
            popularity = popularity,
            original_name = original_name
        )
    }
}