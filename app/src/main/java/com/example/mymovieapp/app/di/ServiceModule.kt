package com.example.mymovieapp.app.di

import com.example.data.cloud.server.MovieApi
import com.example.data.cloud.server.PersonApi
import com.example.data.cloud.server.VideoApi
import com.example.data.data.provide.MakeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideMovieApi(
        makeService: MakeService
    ): MovieApi = makeService.service(MovieApi::class.java)

    @Provides
    fun providePersonApi(
        makeService: MakeService
    ): PersonApi = makeService.service(PersonApi::class.java)

    @Provides
    fun provideVideoApi(
        makeService: MakeService
    ): VideoApi = makeService.service(VideoApi::class.java)

}