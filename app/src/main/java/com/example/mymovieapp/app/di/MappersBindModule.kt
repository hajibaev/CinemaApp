package com.example.mymovieapp.app.di

import com.example.data.cloud.mappers.movie.*
import com.example.data.cloud.mappers.person.MapCrewCloudToData
import com.example.data.cloud.mappers.person.MapPersonCloudToData
import com.example.data.cloud.mappers.person.MapPersonDetailsCloudToData
import com.example.data.cloud.mappers.person.MapPersonsCloudToData
import com.example.data.cloud.models.movie.*
import com.example.data.cloud.models.person.*
import com.example.data.data.mappers.movie.*
import com.example.data.data.mappers.person.MapCrewDataToDomain
import com.example.data.data.mappers.person.MapPersonDataToDomain
import com.example.data.data.mappers.person.MapPersonDetailsDataToDomain
import com.example.data.data.mappers.person.MapPersonsDataToDomain
import com.example.data.data.models.movie.*
import com.example.data.data.models.person.*
import com.example.data.storage.movie.MovieStorage
import com.example.data.storage.movie.mappers.MapMovieDataToStorage
import com.example.data.storage.movie.mappers.MapMovieDomainToData
import com.example.data.storage.movie.mappers.MapMovieStorageToData
import com.example.data.storage.tv.mappers.MapSeriesDataToStorage
import com.example.data.storage.tv.mappers.MapTvStorageToDataMaps
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.base.Mapper
import com.example.domain.models.movie.*
import com.example.domain.models.person.*
import com.example.mymovieapp.app.mappers.movie.*
import com.example.mymovieapp.app.mappers.person.MapCrewDomainToUi
import com.example.mymovieapp.app.mappers.person.MapPersonDetailsDomainToUi
import com.example.mymovieapp.app.mappers.person.MapPersonDomainToUi
import com.example.mymovieapp.app.mappers.person.MapPersonsDomainToUi
import com.example.mymovieapp.app.models.movie.*
import com.example.mymovieapp.app.models.person.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class MappersBindModule {

    @Binds
    abstract fun bindMapCastCloudToData(impl: MapCastCloudToData): Mapper<CastCloud, CastData>

    @Binds
    abstract fun bindMapCreditsResponseCloudToData(impl: MapCreditsResponseCloudToData): Mapper<CreditsResponseCloud, CreditsResponseData>

    @Binds
    abstract fun bindMapCastDataToDomain(impl: MapCastDataToDomain): Mapper<CastData, CastDomain>

    @Binds
    abstract fun bindMapCreditsResponseDataToDomain(impl: MapCreditsResponseDataToDomain): Mapper<CreditsResponseData, CreditsResponseDomain>

    @Binds
    abstract fun bindMapCastDomainToUi(impl: MapCastDomainToUi): Mapper<CastDomain, CastUi>

    @Binds
    abstract fun bindMapCreditsResponseDomainToUi(impl: MapCreditsResponseDomainToUi): Mapper<CreditsResponseDomain, CreditsResponseUi>

    @Binds
    abstract fun bindMapFromMovieCloudToData(impl: MapMovieCloudToData): Mapper<MovieCloud, MovieData>

    @Binds
    abstract fun bindMapCrewCloudToData(impl: MapCrewCloudToData): Mapper<CrewCloud, CrewData>

    @Binds
    abstract fun bindMapCrewDomainToUi(impl: MapCrewDomainToUi): Mapper<CrewDomain, CrewUi>

    @Binds
    abstract fun bindMapCrewDataToDomain(impl: MapCrewDataToDomain): Mapper<CrewData, CrewDomain>

    @Binds
    abstract fun bindMapFromMovieDetailsCloudToData(impl: MapMovieDetailsCloudToData): Mapper<MovieDetailsCloud, MovieDetailsData>

    @Binds
    abstract fun bindMapFromMoviesCloudToData(impl: MapMoviesCloudToData): Mapper<MoviesResponseCloud, MoviesData>

    @Binds
    abstract fun bindMapFromPersonCloudToData(impl: MapPersonCloudToData): Mapper<PersonCloud, PersonData>

    @Binds
    abstract fun bindMapFromPersonDetailsCloudToData(impl: MapPersonDetailsCloudToData): Mapper<PersonDetailsCloud, PersonDetailsData>

    @Binds
    abstract fun bindMapFromPersonsCloudToData(impl: MapPersonsCloudToData): Mapper<PersonsCloud, PersonsData>

    @Binds
    abstract fun bindMapFromMovieDataToDomain(impl: MapMovieDataToDomain): Mapper<MovieData, MovieDomain>

    @Binds
    abstract fun bindMapFromMovieDetailsDataToDomain(impl: MapMovieDetailsDataToDomain): Mapper<MovieDetailsData, MovieDetailsDomain>

    @Binds
    abstract fun bindMapFromMoviesDataToDomain(impl: MapMoviesDataToDomain): Mapper<MoviesData, MoviesResponseDomain>

    @Binds
    abstract fun bindMapFromPersonDataToDomain(impl: MapPersonDataToDomain): Mapper<PersonData, PersonDomain>

    @Binds
    abstract fun bindMapFromPersonDetailsDataToDomain(impl: MapPersonDetailsDataToDomain): Mapper<PersonDetailsData, PersonDetailsDomain>

    @Binds
    abstract fun bindMapFromPersonsDataToDomain(impl: MapPersonsDataToDomain): Mapper<PersonsData, PersonsDomain>

    @Binds
    abstract fun bindMapFromDomainToData(impl: MapMovieDomainToData): Mapper<MovieDomain, MovieData>

    @Binds
    abstract fun bindMapMovieFromStorageToData(impl: MapMovieStorageToData): Mapper<MovieStorage, MovieData>

    @Binds
    abstract fun bindMapMovieToStorage(impl: MapMovieDataToStorage): Mapper<MovieData, MovieStorage>

    @Binds
    abstract fun bindMapSeriesDataToStorage(impl: MapSeriesDataToStorage): Mapper<SeriesData, TvStorage>

    @Binds
    abstract fun bindMapTvStorageToDataMaps(impl: MapTvStorageToDataMaps): Mapper<TvStorage, SeriesData>

    @Binds
    abstract fun bindMapFromMovieDomainToPresentation(impl: MapMovieDomainToUi): Mapper<MovieDomain, MovieUi>

    @Binds
    abstract fun bindMapFromMoviesDomainToPresentation(impl: MapMoviesDomainToUi): Mapper<MoviesResponseDomain, MoviesResponseUi>

    @Binds
    abstract fun bindMapMovieUiToDomain(impl: MapMovieUiToDomain): Mapper<MovieUi, MovieDomain>

    @Binds
    abstract fun bindMapFromPersonDomainToPresentation(impl: MapPersonDomainToUi): Mapper<PersonDomain, PersonPresentation>

    @Binds
    abstract fun bindMapPersonsDomainToUi(impl: MapPersonsDomainToUi): Mapper<PersonsDomain, PersonsPresentation>

    @Binds
    abstract fun bindMapMovieDetailsDomainToUi(impl: MapMovieDetailsDomainToUi): Mapper<MovieDetailsDomain, MovieDetailsUi>

    @Binds
    abstract fun bindMapPersonDetailsDomainToUi(impl: MapPersonDetailsDomainToUi): Mapper<PersonDetailsDomain, PersonDetailsPresentation>

    @Binds
    abstract fun bindMapSeriesCloudToData(impl: MapSeriesCloudToData): Mapper<SeriesCloud, SeriesData>

    @Binds
    abstract fun bindMapTvResponseCloudData(impl: MapTvResponseCloudData): Mapper<TvSeriesResponseCloud, TvSeriesResponseData>

    @Binds
    abstract fun bindMapSeriesDataToDomain(impl: MapSeriesDataToDomain): Mapper<SeriesData, SeriesDomain>

    @Binds
    abstract fun bindMapSeriesDomainToData(impl: MapSeriesDomainToData): Mapper<SeriesDomain, SeriesData>

    @Binds
    abstract fun bindMapTvResponseDataToDomain(impl: MapTvResponseDataToDomain): Mapper<TvSeriesResponseData, TvSeriesResponseDomain>

    @Binds
    abstract fun bindMapSeriesDomainToUi(impl: MapSeriesDomainToUi): Mapper<SeriesDomain, SeriesUi>

    @Binds
    abstract fun bindMapSeriesUiToDomain(impl: MapSeriesUiToDomain): Mapper<SeriesUi, SeriesDomain>

    @Binds
    abstract fun bindMapTvResponseDomainToUi(impl: MapTvResponseDomainToUi): Mapper<TvSeriesResponseDomain, TvSeriesResponseUi>

    @Binds
    abstract fun bindMapTvDetailsCloudToData(impl: MapTvDetailsCloudToData): Mapper<TvSeriesDetailsCloud, TvSeriesDetailsData>

    @Binds
    abstract fun bindMapTvDetailsDataToDomain(impl: MapTvDetailsDataToDomain): Mapper<TvSeriesDetailsData, TvSeriesDetailsDomain>

    @Binds
    abstract fun bindMapTvSeriesDetailsDomainToUi(impl: MapTvSeriesDetailsDomainToUi): Mapper<TvSeriesDetailsDomain, TvSeriesDetailsUi>

}