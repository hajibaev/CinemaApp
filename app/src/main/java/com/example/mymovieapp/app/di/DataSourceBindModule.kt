package com.example.mymovieapp.app.di

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
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceBindModule {

    @Binds
    abstract fun provideResponseHandlerImpl(impl: ResponseHandlerImpl): ResponseHandler

    @Binds
    abstract fun provideMoviesCloudDataSource(
        impl: MoviesCloudDataImpl
    ): MoviesCloudDataSource

    @Binds
    abstract fun provideMovieStorageCloudDataSource(
        impl: MovieSourceCloudDataImpl
    ): StorageCloudDataSource

    @Binds
    abstract fun providePersonsCloudDataSource(
        impl: PersonsCloudDataImpl
    ): PersonsCloudDataSource

    @Binds
    abstract fun provideVideoCloudDataSource(
        impl: VideoCloudDataSourceImpl
    ): VideoCloudDataSource
}