package com.example.data.cloud.source.person

import com.example.data.models.person.PersonDetailsData
import com.example.data.models.person.PersonsData
import com.example.domain.DataRequestState
import kotlinx.coroutines.flow.Flow

interface PersonsCloudDataSource {
    fun getAllPeople(page: Int): Flow<PersonsData>
    fun getAllPersonDetails(personId: Int): Flow<PersonDetailsData>
    suspend fun getAllSearchPeopleMovies(query: String): Flow<PersonsData>
}