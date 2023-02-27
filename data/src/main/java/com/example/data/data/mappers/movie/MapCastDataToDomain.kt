package com.example.data.data.mappers.movie

import com.example.data.data.models.person.CastData
import com.example.domain.base.Mapper
import com.example.domain.models.person.CastDomain
import javax.inject.Inject

class MapCastDataToDomain @Inject constructor() : Mapper<CastData, CastDomain> {
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