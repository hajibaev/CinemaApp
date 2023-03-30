package com.example.data.cloud.source.person

import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import kotlinx.coroutines.flow.Flow

interface PersonsDataSource {

    fun fetchAllPeople(page: Int): Flow<PersonsData>

    fun fetchAllPersonDetails(personId: Int): Flow<PersonDetailsData>

    fun fetchAllSearchPeopleMovies(query: String): Flow<PersonsData>
}