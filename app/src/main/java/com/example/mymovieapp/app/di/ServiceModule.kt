package com.example.mymovieapp.app.di

import com.example.data.cloud.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailsService(retrofit: Retrofit): MovieDetailsService =
        retrofit.create(MovieDetailsService::class.java)

    @Provides
    @Singleton
    fun provideTvService(retrofit: Retrofit): TvService =
        retrofit.create(TvService::class.java)

    @Provides
    @Singleton
    fun provideTvDetailsService(retrofit: Retrofit): TvDetailsService =
        retrofit.create(TvDetailsService::class.java)

    @Provides
    @Singleton
    fun providePersonService(retrofit: Retrofit): PersonService =
        retrofit.create(PersonService::class.java)

}