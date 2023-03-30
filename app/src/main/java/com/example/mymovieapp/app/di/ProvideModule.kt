package com.example.mymovieapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//
//@Module
//@InstallIn(SingletonComponent::class)
//class ProvideModule {
//
//    @Provides
//    fun provideMakeService(
//        retrofitBuilder: ProvideRetrofitBuilder
//    ): MakeService = MakeServiceImpl(retrofitBuilder = retrofitBuilder)
//
//
//    @Provides
//    fun provideProvideConverterFactory(): ProvideConverterFactory = ProvideConverterFactoryImpl()
//
//    @Provides
//    fun provideProvideInterceptorDebug(): ProvideInterceptor = ProvideInterceptorImpl.Debug()
//
//
//    @Provides
//    fun provideProvideOkHttpClientBuilder(provideInterceptor: ProvideInterceptor):
//            ProvideOkHttpClientBuilder =
//        ProvideOkHttpClientBuilderImpl(provideInterceptor = provideInterceptor)
//
//    @Provides
//    fun provideProvideRetrofitBuilder(
//        provideConverterFactory: ProvideConverterFactory,
//        provideOkHttpClientBuilder: ProvideOkHttpClientBuilder
//    ): ProvideRetrofitBuilder =
//        ProvideRetrofitBuilderImpl(
//            provideConverterFactory = provideConverterFactory,
//            provideOkHttpClientBuilder = provideOkHttpClientBuilder
//        )
//
//}


const val API_KEY = "3249dba9ba8a81c53f42a124fe89e8e5"
const val CONNECT_TIMEOUT_SECONDS = 30L
const val BASE_URL = "https://api.themoviedb.org/3/"
const val LANGUAGE = "en"

@Module
@InstallIn(SingletonComponent::class)
object ProvideModule {


    @Provides
    @Singleton
    fun requestInterceptor() = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .cacheControl(CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build())
//            .header("api_key", API_KEY)
//            .header("language", LANGUAGE)
            .build()
        return@Interceptor chain.proceed(request)
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    fun okHttpClient(
        requestInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun postRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
