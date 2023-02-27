package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.person.CastCloud
import com.example.data.cloud.models.person.CreditsResponseCloud
import com.example.data.cloud.models.person.CrewCloud
import com.example.data.data.models.person.CastData
import com.example.data.data.models.person.CreditsResponseData
import com.example.data.data.models.person.CrewData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapCreditsResponseCloudToData @Inject constructor(
    private val mapListCastCloudToData: Mapper<List<CastCloud>, List<CastData>>,
    private val mapListCrewCloudToData: Mapper<List<CrewCloud>, List<CrewData>>
) :
    Mapper<CreditsResponseCloud, CreditsResponseData> {
    override fun map(from: CreditsResponseCloud) = from.run {
        CreditsResponseData(
            cast = mapListCastCloudToData.map(cast),
            crew = mapListCrewCloudToData.map(crew),
            id = id
        )
    }
}