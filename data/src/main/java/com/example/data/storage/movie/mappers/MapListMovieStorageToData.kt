package com.example.data.storage.movie.mappers

import com.example.data.data.models.movie.MovieData
import com.example.data.storage.movie.MovieStorage
import com.example.domain.base.Mapper

class MapListMovieStorageToData (private val mapper: Mapper<MovieStorage, MovieData>) :
    Mapper<List<MovieStorage>, List<MovieData>> {
    override fun map(from: List<MovieStorage>) = from.map {
        mapper.map(it)
    }
}


