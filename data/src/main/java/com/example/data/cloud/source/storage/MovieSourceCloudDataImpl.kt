package com.example.data.cloud.source.storage

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.SeriesData
import com.example.data.storage.movie.MovieStorage
import com.example.data.storage.movie.room.MovieDao
import com.example.data.storage.tv.models.TvStorage
import com.example.data.storage.tv.room.TvDao
import com.example.domain.base.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieSourceCloudDataImpl @Inject constructor(
    private val dao: MovieDao,
    private val tvDao: TvDao,
    private val mapperMovieDataToStorage: Mapper<MovieData, MovieStorage>,
    private val mapperListMovieStorageToData: Mapper<List<MovieStorage>, List<MovieData>>,
    private val mapperSeriesDataToStorage: Mapper<SeriesData, TvStorage>,
    private val mapperListTvStorageToData: Mapper<List<TvStorage>, List<SeriesData>>,
) : StorageCloudDataSource {

    override suspend fun save(movie: MovieData) =
        withContext(Dispatchers.IO) {
            dao.saveMovie(mapperMovieDataToStorage.map(movie))
        }

    override suspend fun delete(movieId: Int) =
        withContext(Dispatchers.IO) {
            dao.deleteMovieFromSaveStorage(movieId)
        }

    override fun getStorageMovies(): Flow<List<MovieData>> =
        dao.getStorageMovies()
            .flowOn(Dispatchers.IO)
            .map(mapperListMovieStorageToData::map)
            .flowOn(Dispatchers.Default)

    override suspend fun saveTv(tv: SeriesData) =
        withContext(Dispatchers.IO) {
            tvDao.saveTv(mapperSeriesDataToStorage.map(tv))
        }

    override suspend fun deleteTV(id: Int) =
        withContext(Dispatchers.IO) {
            tvDao.deleteTVFromSaveStorage(id)
        }

    override fun getTvStorage(): Flow<List<SeriesData>> =
        tvDao.getTvStorage()
            .flowOn(Dispatchers.IO)
            .map(mapperListTvStorageToData::map)
            .flowOn(Dispatchers.Default)

}

