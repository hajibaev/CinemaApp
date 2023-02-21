package com.example.mymovieapp.app.di

import com.example.data.data.repository.LanguageRepositoryImpl
import com.example.data.data.repository.MovieRepositoryImpl
import com.example.data.data.repository.PersonRepositoryImpl
import com.example.data.data.repository.TrailerRepositoryImpl
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
    abstract fun provideMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun provideMovieStorageRepository(
        impl: StorageRepositoryImpl
    ): MovieStorageRepository

    @Binds
    abstract fun providePersonRepository(
        impl: PersonRepositoryImpl
    ): PersonRepository

    @Binds
    abstract fun provideVideoRepository(
        impl: TrailerRepositoryImpl
    ): VideoRepository

    @Binds
    abstract fun provideLanguage(
        impl: LanguageRepositoryImpl
    ): LanguageRepository

}
