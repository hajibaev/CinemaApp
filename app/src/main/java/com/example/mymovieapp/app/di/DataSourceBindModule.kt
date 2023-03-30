package com.example.mymovieapp.app.di

import com.example.data.cloud.source.handler.ResponseHandler
import com.example.data.cloud.source.handler.ResponseHandlerImpl
import com.example.data.cloud.source.movie.*
import com.example.data.cloud.source.person.PersonsDataSource
import com.example.data.cloud.source.person.PersonsDataSourceImpl
import com.example.data.cloud.source.storage.StorageDataSource
import com.example.data.cloud.source.storage.StorageDataSourceImpl
import com.example.data.cloud.source.tv.TvDataSource
import com.example.data.cloud.source.tv.TvDataSourceImpl
import com.example.data.cloud.source.tv.TvDetailsDataSource
import com.example.data.cloud.source.tv.TvDetailsDataSourceImpl
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
    abstract fun provideSearchDataSource(
        impl: SearchDataSourceImpl
    ): SearchDataSource

    @Binds
    abstract fun provideMovieDataSource(
        impl: MovieDataSourceImpl
    ): MovieDataSource

    @Binds
    abstract fun provideMovieDetailsDataSource(
        impl: MovieDetailsDataSourceImpl
    ): MovieDetailsDataSource

    @Binds
    abstract fun provideTvDataSource(
        impl: TvDataSourceImpl
    ): TvDataSource

    @Binds
    abstract fun provideTvDetailsDataSource(
        impl: TvDetailsDataSourceImpl
    ): TvDetailsDataSource

    @Binds
    abstract fun provideStorageDataSource(
        impl: StorageDataSourceImpl
    ): StorageDataSource

    @Binds
    abstract fun providePersonsDataSource(
        impl: PersonsDataSourceImpl
    ): PersonsDataSource

}