package com.example.data.mappers.movie

import com.example.data.models.movie.GenresData
import com.example.domain.Maps
import com.example.domain.models.movie.GenresDomain

class MapGenresDataToDomain : Maps<GenresData, GenresDomain> {
    override fun map(from: GenresData) = from.run {
        GenresDomain(
            id = id,
            name = name
        )
    }
}