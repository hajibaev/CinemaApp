package com.example.mymovieapp.di

import com.example.data.cloud.api.MovieApi
import com.example.data.cloud.api.PersonApi
import com.example.data.cloud.api.VideoApi
import com.example.data.provide.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ProvideModule {

    @Provides
    fun provideMakeService(
        retrofitBuilder: ProvideRetrofitBuilder
    ): MakeService = MakeServiceImpl(retrofitBuilder = retrofitBuilder)

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


    @Provides
    fun provideProvideConverterFactory(): ProvideConverterFactory = ProvideConverterFactoryImpl()

    @Provides
    fun provideProvideInterceptorDebug(): ProvideInterceptor = ProvideInterceptorImpl.Debug()


    @Provides
    fun provideProvideOkHttpClientBuilder(provideInterceptor: ProvideInterceptor):
            ProvideOkHttpClientBuilder =
        ProvideOkHttpClientBuilderImpl(provideInterceptor = provideInterceptor)

    @Provides
    fun provideProvideRetrofitBuilder(
        provideConverterFactory: ProvideConverterFactory,
        provideOkHttpClientBuilder: ProvideOkHttpClientBuilder
    ): ProvideRetrofitBuilder =
        ProvideRetrofitBuilderImpl(
            provideConverterFactory = provideConverterFactory,
            provideOkHttpClientBuilder = provideOkHttpClientBuilder
        )

}