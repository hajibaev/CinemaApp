package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.CastCloud
import com.example.data.cloud.models.movie.CreditsResponseCloud
import com.example.data.models.movie.CastData
import com.example.data.models.movie.CreditsResponseData
import com.example.domain.Maps

class MapCreditsResponseCloudToData(
    private val mapListCastCloudToData: Maps<List<CastCloud>, List<CastData>>,
) : Maps<CreditsResponseCloud, CreditsResponseData> {
    override fun map(from: CreditsResponseCloud) = from.run {
        CreditsResponseData(
            cast = mapListCastCloudToData.map(cast),
            id = id
        )
    }
}