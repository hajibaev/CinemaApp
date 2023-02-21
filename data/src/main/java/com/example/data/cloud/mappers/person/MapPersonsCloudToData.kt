package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.data.models.person.PersonData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapPersonsCloudToData @Inject constructor(private val mapper: Mapper<List<PersonCloud>, List<PersonData>>) :
    Mapper<PersonsCloud, PersonsData> {
    override fun map(from: PersonsCloud) = from.run {
        PersonsData(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}