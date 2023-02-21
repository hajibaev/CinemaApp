package com.example.mymovieapp.app.mappers.movie

import com.example.domain.base.Mapper
import com.example.domain.models.movie.CastDomain
import com.example.mymovieapp.app.models.movie.CastUi
import javax.inject.Inject

class MapCastDomainToUi @Inject constructor() : Mapper<CastDomain, CastUi> {
    override fun map(from: CastDomain) = from.run {
        CastUi(
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