package com.example.data.mappers.movie

import com.example.data.models.movie.GenresData
import com.example.domain.Maps
import com.example.domain.models.movie.GenresDomain

class MapListGenresDataToDomain(private val mapper: Maps<GenresData, GenresDomain>) :
    Maps<List<GenresData>, List<GenresDomain>> {
    override fun map(from: List<GenresData>) = from.map {
        mapper.map(it)
    }
}