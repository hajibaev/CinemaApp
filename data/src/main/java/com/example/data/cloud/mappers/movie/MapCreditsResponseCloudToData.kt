package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.CastCloud
import com.example.data.cloud.models.movie.CreditsResponseCloud
import com.example.data.data.models.movie.CastData
import com.example.data.data.models.movie.CreditsResponseData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapCreditsResponseCloudToData @Inject constructor(private val mapListCastCloudToData: Mapper<List<CastCloud>, List<CastData>>) :
    Mapper<CreditsResponseCloud, CreditsResponseData> {
    override fun map(from: CreditsResponseCloud) = from.run {
        CreditsResponseData(
            cast = mapListCastCloudToData.map(cast),
            id = id
        )
    }
}