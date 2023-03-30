package com.example.data.cloud.source.person

import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.cloud.service.PersonService
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonsDataSourceImpl @Inject constructor(
    private val api: PersonService,
    private val mapPersonsCloudToData: Mapper<PersonsCloud, PersonsData>,
    private val mapDetailsCloudToData: Mapper<PersonDetailsCloud, PersonDetailsData>,
    private val dispatchers: DispatchersProvider
) : PersonsDataSource {

    override fun fetchAllPeople(page: Int): Flow<PersonsData> = flow {
        emit(api.getPeople(page = page))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapPersonsCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllPersonDetails(personId: Int): Flow<PersonDetailsData> = flow {
        emit(api.getPersonDetails(id = personId))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapDetailsCloudToData::map)
        .flowOn(dispatchers.default())

    override fun fetchAllSearchPeopleMovies(query: String): Flow<PersonsData> = flow {
        emit(api.getSearchPeople(query = query))
    }.flowOn(dispatchers.io()).map { it.body()!! }.map(mapPersonsCloudToData::map)
        .flowOn(dispatchers.default())

}
