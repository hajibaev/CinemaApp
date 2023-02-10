package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.GenresCloud
import com.example.data.models.movie.GenresData
import com.example.domain.Maps

class MapGenresCloudToData : Maps<GenresCloud, GenresData> {
    override fun map(from: GenresCloud) = from.run {
        GenresData(
            id = id,
            name = name
        )
    }
}