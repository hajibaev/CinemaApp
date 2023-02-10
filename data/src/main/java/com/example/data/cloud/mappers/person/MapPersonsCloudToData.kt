package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.models.person.PersonData
import com.example.data.models.person.PersonsData
import com.example.domain.Maps

class MapPersonsCloudToData(private val mapper: Maps<List<PersonCloud>, List<PersonData>>) :
    Maps<PersonsCloud, PersonsData> {
    override fun map(from: PersonsCloud) = from.run {
        PersonsData(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}