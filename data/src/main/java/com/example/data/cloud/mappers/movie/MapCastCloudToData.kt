package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.person.CastCloud
import com.example.data.data.models.person.CastData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapCastCloudToData @Inject constructor() : Mapper<CastCloud, CastData> {
    override fun map(from: CastCloud) = from.run {
        CastData(
            adult = adult,
            castId = castId,
            character = character,
            creditId = creditId,
            gender = gender,
            id = id,
            knownForDepartment = knownForDepartment,
            name = name,
            order = order,
            originalName = originalName,
            popularity = popularity,
            profilePath = profilePath
        )
    }
}