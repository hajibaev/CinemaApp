package com.example.data.storage.movie.mappers

import com.example.data.models.movie.MovieData
import com.example.data.storage.movie.MovieStorage
import com.example.domain.Maps

class MapListMovieStorageToData(private val mapper: Maps<MovieStorage, MovieData>) :
    Maps<List<MovieStorage>, List<MovieData>> {
    override fun map(from: List<MovieStorage>) = from.map {
        mapper.map(it)
    }
}


