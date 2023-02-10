package com.example.mymovieapp.mappers.movie

import com.example.domain.Maps
import com.example.domain.models.movie.CastDomain
import com.example.mymovieapp.models.movie.CastUi

class MapCastDomainToUi : Maps<CastDomain, CastUi> {
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