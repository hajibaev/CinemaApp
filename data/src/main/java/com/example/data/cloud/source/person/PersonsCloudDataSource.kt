package com.example.data.cloud.source.person

import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import kotlinx.coroutines.flow.Flow

interface PersonsCloudDataSource {
    fun getAllPeople(page: Int): Flow<PersonsData>
    fun getAllPersonDetails(personId: Int): Flow<PersonDetailsData>
    suspend fun getAllSearchPeopleMovies(query: String): Flow<PersonsData>
}