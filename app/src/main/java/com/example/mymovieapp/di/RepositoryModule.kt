package com.example.mymovieapp.di

import android.content.Context
import com.example.data.cloud.source.movie.MoviesCloudDataSource
import com.example.data.cloud.source.person.PersonsCloudDataSource
import com.example.data.cloud.source.storage.StorageCloudDataSource
import com.example.data.cloud.source.video.VideoCloudDataSource
import com.example.data.models.movie.*
import com.example.data.models.person.PersonDetailsData
import com.example.data.models.person.PersonsData
import com.example.data.models.video.VideoData
import com.example.data.repository.LanguageRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.data.repository.PersonRepositoryImpl
import com.example.data.repository.TrailerRepositoryImpl
import com.example.data.storage.repository.StorageRepositoryImpl
import com.example.domain.Maps
import com.example.domain.models.movie.*
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonsDomain
import com.example.domain.models.video.VideosDomain
import com.example.domain.repository.*
import com.example.mymovieapp.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideResourceProvider(
        @ApplicationContext context: Context,
    ): ResourceProvider = ResourceProvider.Base(context = context)
//  @Provides
//    fun provideResource(
//        @ApplicationContext context: Context,
//    ): HandleException = HandleException.Base()

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesCloudDataSource: MoviesCloudDataSource,
        mapMoviesDataToDomain: Maps<MoviesData, MoviesResponseDomain>,
        mapMovieDetailsToDomain: Maps<MovieDetailsData, MovieDetailsDomain>,
        mapCreditsResponseDataDomain: Maps<CreditsResponseData, CreditsResponseDomain>,
        mapTvResponseDataToDomain: Maps<TvSeriesResponseData, TvSeriesResponseDomain>,
        mapTvDetailsDataToDomain: Maps<TvSeriesDetailsData, TvSeriesDetailsDomain>,
    ): MovieRepository =
        MovieRepositoryImpl(
            moviesCloudDataSource = moviesCloudDataSource,
            mapMoviesDataToDomain = mapMoviesDataToDomain,
            mapMovieDetailsToDomain = mapMovieDetailsToDomain,
            mapCreditsResponseDataDomain = mapCreditsResponseDataDomain,
            mapTvResponseDataToDomain = mapTvResponseDataToDomain,
            mapTvDetailsDataToDomain = mapTvDetailsDataToDomain,
        )

    @Provides
    @Singleton
    fun provideMovieStorageRepository(
        storage: StorageCloudDataSource,
        mapperMovieDomainToData: Maps<MovieDomain, MovieData>,
        mapMovieDataToDomain: Maps<MovieData, MovieDomain>,
        mapperSeriesDomainToData: Maps<SeriesDomain, SeriesData>,
        mapSeriesDataToDomain: Maps<SeriesData, SeriesDomain>,
    ): MovieStorageRepository =
        StorageRepositoryImpl(
            storage = storage,
            mapperMovieDomainToData = mapperMovieDomainToData,
            mapMovieDataToDomain = mapMovieDataToDomain,
            mapperSeriesDomainToData = mapperSeriesDomainToData,
            mapSeriesDataToDomain = mapSeriesDataToDomain,
        )


    @Provides
    @Singleton
    fun providePersonRepository(
        personsCloudDataSource: PersonsCloudDataSource,
        mapPersonsDataToDomain: Maps<PersonsData, PersonsDomain>,
        mapPersonDetailsToDomain: Maps<PersonDetailsData, PersonDetailsDomain>
    ): PersonRepository =
        PersonRepositoryImpl(
            personsCloudDataSource = personsCloudDataSource,
            mapPersonsDataToDomain = mapPersonsDataToDomain,
            mapPersonDetailsToDomain = mapPersonDetailsToDomain
        )

    @Provides
    @Singleton
    fun provideVideoRepository(
        video: VideoCloudDataSource,
        videosMapper: Maps<VideoData, VideosDomain>
    ): VideoRepository =
        TrailerRepositoryImpl(
            video = video,
            videosMapper = videosMapper,
        )

    @Provides
    @Singleton
    fun provideLanguage(
        @ApplicationContext context: Context
    ): LanguageRepository = LanguageRepositoryImpl(
        context = context
    )
}
