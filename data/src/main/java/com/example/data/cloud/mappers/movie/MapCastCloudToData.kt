package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.CastCloud
import com.example.data.models.movie.CastData
import com.example.domain.Maps

class MapCastCloudToData : Maps<CastCloud, CastData> {
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