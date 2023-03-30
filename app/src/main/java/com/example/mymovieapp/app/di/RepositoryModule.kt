package com.example.mymovieapp.app.di

import com.example.data.data.repository.PersonRepositoryImpl
import com.example.data.data.repository.movie.MovieDetailsRepositoryImpl
import com.example.data.data.repository.movie.MovieRepositoryImpl
import com.example.data.data.repository.movie.SearchRepositoryImpl
import com.example.data.data.repository.tv.TvDetailsRepositoryImpl
import com.example.data.data.repository.tv.TvRepositoryImpl
import com.example.data.storage.repository.StorageRepositoryImpl
import com.example.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideSearchRepository(
        impl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    abstract fun provideMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun provideMovieDetailsRepository(
        impl: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

    @Binds
    abstract fun provideMovieStorageRepository(
        impl: StorageRepositoryImpl
    ): MovieStorageRepository

    @Binds
    abstract fun provideTvRepository(
        impl: TvRepositoryImpl
    ): TvRepository

    @Binds
    abstract fun provideTvDetailsRepository(
        impl: TvDetailsRepositoryImpl
    ): TvDetailsRepository


    @Binds
    abstract fun providePersonRepository(
        impl: PersonRepositoryImpl
    ): PersonRepository


}
