package com.example.data.mappers.movie

import com.example.data.models.movie.CastData
import com.example.domain.Maps
import com.example.domain.models.movie.CastDomain

class MapCastDataToDomain : Maps<CastData, CastDomain> {
    override fun map(from: CastData) = from.run {
        CastDomain(
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