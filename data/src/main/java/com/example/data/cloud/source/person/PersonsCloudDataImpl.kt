package com.example.data.cloud.source.person

import com.example.data.cloud.server.PersonApi
import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonsCloudDataImpl @Inject constructor(
    private val api: PersonApi,
    private val mapPersonsCloudToData: Mapper<PersonsCloud, PersonsData>,
    private val mapDetailsCloudToData: Mapper<PersonDetailsCloud, PersonDetailsData>,
) : PersonsCloudDataSource {
    override fun getAllPeople(page: Int): Flow<PersonsData> = flow {
        emit(api.getPeople(page = page))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapPersonsCloudToData::map)
        .flowOn(Dispatchers.Default)

    override fun getAllPersonDetails(personId: Int): Flow<PersonDetailsData> = flow {
        emit(api.getPersonDetails(id = personId))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapDetailsCloudToData::map)
        .flowOn(Dispatchers.Default)

    override suspend fun getAllSearchPeopleMovies(query: String): Flow<PersonsData> = flow {
        emit(api.getSearchPeople(query = query))
    }.flowOn(Dispatchers.IO).map { it.body()!! }.map(mapPersonsCloudToData::map)
        .flowOn(Dispatchers.Default)
}