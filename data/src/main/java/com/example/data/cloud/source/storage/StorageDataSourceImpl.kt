package com.example.data.cloud.source.storage

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.SeriesData
import com.example.data.storage.movie.MovieStorage
import com.example.data.storage.room.MovieDao
import com.example.data.storage.tv.models.TvStorage
import com.example.data.storage.room.TvDao
import com.example.domain.base.Mapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageDataSourceImpl @Inject constructor(
    private val dao: MovieDao,
    private val tvDao: TvDao,
    private val dispatchers: DispatchersProvider,
    private val mapperMovieDataToStorage: Mapper<MovieData, MovieStorage>,
    private val mapperListMovieStorageToData: Mapper<List<MovieStorage>, List<MovieData>>,
    private val mapperSeriesDataToStorage: Mapper<SeriesData, TvStorage>,
    private val mapperListTvStorageToData: Mapper<List<TvStorage>, List<SeriesData>>,
) : StorageDataSource {

    override suspend fun save(movie: MovieData) {
        withContext(dispatchers.io()) {
            dao.saveMovie(mapperMovieDataToStorage.map(movie))
        }
    }

    override suspend fun delete(movieId: Int) =
        withContext(dispatchers.io()) {
            dao.deleteMovieFromSaveStorage(movieId)
        }

    override fun getStorageMovies(): Flow<List<MovieData>> =
        dao.getStorageMovies().flowOn(dispatchers.io()).map(mapperListMovieStorageToData::map)
            .flowOn(dispatchers.default())

    override suspend fun saveTv(tv: SeriesData) =
        withContext((dispatchers.io())) {
            tvDao.saveTv(mapperSeriesDataToStorage.map(tv))
        }

    override suspend fun deleteTV(id: Int) =
        withContext((dispatchers.io())) {
            tvDao.deleteTVFromSaveStorage(id)
        }

    override fun getTvStorage(): Flow<List<SeriesData>> =
        tvDao.getTvStorage().flowOn(dispatchers.io())
            .map(mapperListTvStorageToData::map)
            .flowOn(dispatchers.default())

}

