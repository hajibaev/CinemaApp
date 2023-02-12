package com.example.mymovieapp.di

import com.example.data.cloud.api.MovieApi
import com.example.data.cloud.api.PersonApi
import com.example.data.cloud.api.VideoApi
import com.example.data.cloud.models.movie.*
import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.cloud.models.video.VideoCloud
import com.example.data.cloud.source.handler.ResponseHandler
import com.example.data.cloud.source.handler.ResponseHandlerImpl
import com.example.data.cloud.source.movie.MoviesCloudDataImpl
import com.example.data.cloud.source.movie.MoviesCloudDataSource
import com.example.data.cloud.source.person.PersonsCloudDataImpl
import com.example.data.cloud.source.person.PersonsCloudDataSource
import com.example.data.cloud.source.storage.MovieSourceCloudDataImpl
import com.example.data.cloud.source.storage.StorageCloudDataSource
import com.example.data.cloud.source.video.VideoCloudDataSource
import com.example.data.cloud.source.video.VideoCloudDataSourceImpl
import com.example.data.models.movie.*
import com.example.data.models.person.PersonDetailsData
import com.example.data.models.person.PersonsData
import com.example.data.models.video.VideoData
import com.example.data.storage.movie.MovieStorage
import com.example.data.storage.movie.room.MovieDao
import com.example.data.storage.tv.models.TvStorage
import com.example.data.storage.tv.room.TvDao
import com.example.domain.Maps
import com.example.mymovieapp.utils.communication.NavigationCommunication
import com.example.mymovieapp.utils.dispachers.Dispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CloudModule {


    @Provides
    @Singleton
    fun provideNavigationCommunication(): NavigationCommunication = NavigationCommunication.Base()


    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()

    @Provides
    fun provideResponseHandlerImpl(): ResponseHandler = ResponseHandlerImpl()

    @Provides
    @Singleton
    fun provideMoviesCloudDataSource(
        api: MovieApi,
        mapper: Maps<MoviesResponseCloud, MoviesData>,
        mapperDetails: Maps<MovieDetailsCloud, MovieDetailsData>,
        mapCreditsResponseCloudToData: Maps<CreditsResponseCloud, CreditsResponseData>,
        mapTvResponseCloudToData: Maps<TvSeriesResponseCloud, TvSeriesResponseData>,
        mapTvDetailsCloudToData: Maps<TvSeriesDetailsCloud, TvSeriesDetailsData>,
        responseHandler: ResponseHandler
    ): MoviesCloudDataSource =
        MoviesCloudDataImpl(
            api = api,
            mapListMovieCloudToData = mapper,
            mapDetailsCloudToData = mapperDetails,
            responseHandler = responseHandler,
            mapCreditsResponseCloudToData = mapCreditsResponseCloudToData,
            mapTvResponseCloudToData = mapTvResponseCloudToData,
            mapTvDetailsCloudToData = mapTvDetailsCloudToData,
        )

    //
    @Provides
    @Singleton
    fun provideMovieStorageCloudDataSource(
        dao: MovieDao,
        tvDao: TvDao,
        mapperMovieDataToStorage: Maps<MovieData, MovieStorage>,
        mapperListMovieStorageToData: Maps<List<MovieStorage>, List<MovieData>>,
        mapperSeriesDataToStorage: Maps<SeriesData, TvStorage>,
        mapperListTvStorageToData: Maps<List<TvStorage>, List<SeriesData>>,
    ): StorageCloudDataSource =
        MovieSourceCloudDataImpl(
            dao = dao,
            tvDao = tvDao,
            mapperMovieDataToStorage = mapperMovieDataToStorage,
            mapperListMovieStorageToData = mapperListMovieStorageToData,
            mapperSeriesDataToStorage = mapperSeriesDataToStorage,
            mapperListTvStorageToData = mapperListTvStorageToData,
        )


    @Provides
    @Singleton
    fun providePersonsCloudDataSource(
        api: PersonApi,
        mapPersonsCloudToData: Maps<PersonsCloud, PersonsData>,
        mapDetailsCloudToData: Maps<PersonDetailsCloud, PersonDetailsData>,
    ): PersonsCloudDataSource =
        PersonsCloudDataImpl(
            api = api,
            mapPersonsCloudToData = mapPersonsCloudToData,
            mapDetailsCloudToData = mapDetailsCloudToData,
        )

    @Provides
    @Singleton
    fun provideVideoCloudDataSource(
        api: VideoApi,
        mapper: Maps<VideoCloud, VideoData>
    ): VideoCloudDataSource =
        VideoCloudDataSourceImpl(
            api = api,
            mapper = mapper,
        )
}