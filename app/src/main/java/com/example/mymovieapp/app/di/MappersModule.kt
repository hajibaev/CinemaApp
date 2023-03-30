package com.example.mymovieapp.app.di

import com.example.data.cloud.mappers.movie.MapListCastCloudToData
import com.example.data.cloud.mappers.movie.MapListMovieCloudToData
import com.example.data.cloud.mappers.movie.MapListSeriesCloudToData
import com.example.data.cloud.mappers.person.MapListCrewCloudToData
import com.example.data.cloud.mappers.person.MapListPersonCloudToData
import com.example.data.cloud.mappers.person.MapListPersonDetailsCloudToData
import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.cloud.models.movie.SeriesCloud
import com.example.data.cloud.models.person.CastCloud
import com.example.data.cloud.models.person.CrewCloud
import com.example.data.cloud.models.person.PersonCloud
import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.data.mappers.movie.MapListCastDataToDomain
import com.example.data.data.mappers.movie.MapListMovieDataToDomain
import com.example.data.data.mappers.movie.MapListSeriesDataToDomain
import com.example.data.data.mappers.person.MapListCrewDataToDomain
import com.example.data.data.mappers.person.MapListPersonDataToDomain
import com.example.data.data.mappers.person.MapListPersonDetailsDataToDomain
import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.SeriesData
import com.example.data.data.models.person.CastData
import com.example.data.data.models.person.CrewData
import com.example.data.data.models.person.PersonData
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.storage.movie.MovieStorage
import com.example.data.storage.movie.mappers.MapListMovieStorageToData
import com.example.data.storage.tv.mappers.MapListTvStorageToData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.base.Mapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.SeriesDomain
import com.example.domain.models.person.CastDomain
import com.example.domain.models.person.CrewDomain
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonDomain
import com.example.mymovieapp.app.mappers.movie.MapListCastDomainToUi
import com.example.mymovieapp.app.mappers.movie.MapListMovieDomainToUi
import com.example.mymovieapp.app.mappers.movie.MapListSeriesDomainToUi
import com.example.mymovieapp.app.mappers.person.MapListCrewDomainToUi
import com.example.mymovieapp.app.mappers.person.MapListPersonDetailsDomainToUi
import com.example.mymovieapp.app.mappers.person.MapListPersonDomainToUi
import com.example.mymovieapp.app.models.movie.MovieUi
import com.example.mymovieapp.app.models.movie.SeriesUi
import com.example.mymovieapp.app.models.person.CastUi
import com.example.mymovieapp.app.models.person.CrewUi
import com.example.mymovieapp.app.models.person.PersonDetailsPresentation
import com.example.mymovieapp.app.models.person.PersonPresentation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {

    @Provides
    fun bindMapListCastCloudToData(mapper: Mapper<CastCloud, CastData>): Mapper<List<CastCloud>, List<CastData>> =
        MapListCastCloudToData(mapper = mapper)

    @Provides
    fun bindMapListPersonDetailsCloudToData(mapper: Mapper<PersonDetailsCloud, PersonDetailsData>): Mapper<List<PersonDetailsCloud>, List<PersonDetailsData>> =
        MapListPersonDetailsCloudToData(mapper = mapper)

    @Provides
    fun bindMapListCastDataToDomain(mapper: Mapper<CastData, CastDomain>): Mapper<List<CastData>, List<CastDomain>> =
        MapListCastDataToDomain(mapper = mapper)

    @Provides
    fun bindMapListPersonDetailsDataToDomain(mapper: Mapper<PersonDetailsData, PersonDetailsDomain>): Mapper<List<PersonDetailsData>, List<PersonDetailsDomain>> =
        MapListPersonDetailsDataToDomain(mapper = mapper)

    @Provides
    fun bindMapListCastDomainToUi(mapper: Mapper<CastDomain, CastUi>): Mapper<List<CastDomain>, List<CastUi>> =
        MapListCastDomainToUi(mapper = mapper)

    @Provides
    fun bindMapFromMovieListCloudToData(mapper: Mapper<MovieCloud, MovieData>): Mapper<List<MovieCloud>, List<MovieData>> =
        MapListMovieCloudToData(mapper = mapper)

    @Provides
    fun bindMapListCrewCloudToData(mapper: Mapper<CrewCloud, CrewData>): Mapper<List<CrewCloud>, List<CrewData>> =
        MapListCrewCloudToData(mapper = mapper)

    @Provides
    fun bindMapListCrewDataToDomain(mapper: Mapper<CrewData, CrewDomain>):
            Mapper<List<CrewData>, List<CrewDomain>> = MapListCrewDataToDomain(mapper = mapper)

    @Provides
    fun bindMapListCrewDomainToUi(mapper: Mapper<CrewDomain, CrewUi>):
            Mapper<List<CrewDomain>, List<CrewUi>> = MapListCrewDomainToUi(mapper = mapper)

    @Provides
    fun bindMapFromPersonCloudListToData(mapper: Mapper<PersonCloud, PersonData>): Mapper<List<PersonCloud>, List<PersonData>> =
        MapListPersonCloudToData(mapper = mapper)

    @Provides
    fun bindMapFromMovieListDataToDomain(mapper: Mapper<MovieData, MovieDomain>): Mapper<List<MovieData>, List<MovieDomain>> =
        MapListMovieDataToDomain(mapper = mapper)

    @Provides
    fun bindMapFromPersonDataListToDomain(mapper: Mapper<PersonData, PersonDomain>): Mapper<List<PersonData>, List<PersonDomain>> =
        MapListPersonDataToDomain(mapper = mapper)

    @Provides
    fun bindMapMovieListToDomain(mapper: Mapper<MovieStorage, MovieData>): Mapper<List<MovieStorage>, List<MovieData>> =
        MapListMovieStorageToData(mapper = mapper)

    @Provides
    fun bindMapListTvStorageToData(mapper: Mapper<TvStorage, SeriesData>): Mapper<List<TvStorage>, List<SeriesData>> =
        MapListTvStorageToData(mapper = mapper)

    @Provides
    fun bindMapFromMovieListDomainToPresentation(mapper: Mapper<MovieDomain, MovieUi>): Mapper<List<MovieDomain>, List<MovieUi>> =
        MapListMovieDomainToUi(mapper = mapper)

    @Provides
    fun bindMapFromPersonDomainListToPresentation(mapper: Mapper<PersonDomain, PersonPresentation>): Mapper<List<PersonDomain>, List<PersonPresentation>> =
        MapListPersonDomainToUi(mapper = mapper)


    @Provides
    fun bindMapListPersonDetailsDomainToUi(mapper: Mapper<PersonDetailsDomain, PersonDetailsPresentation>): Mapper<List<PersonDetailsDomain>, List<PersonDetailsPresentation>> =
        MapListPersonDetailsDomainToUi(mapper = mapper)


    @Provides
    fun bindMapListSeriesCloudToData(mapper: Mapper<SeriesCloud, SeriesData>): Mapper<List<SeriesCloud>, List<SeriesData>> =
        MapListSeriesCloudToData(mapper = mapper)


    @Provides
    fun bindMapListSeriesDataToDomain(mapper: Mapper<SeriesData, SeriesDomain>): Mapper<List<SeriesData>, List<SeriesDomain>> =
        MapListSeriesDataToDomain(mapper = mapper)

    @Provides
    fun bindMapListSeriesDomainToUi(mapper: Mapper<SeriesDomain, SeriesUi>): Mapper<List<SeriesDomain>, List<SeriesUi>> =
        MapListSeriesDomainToUi(mapper = mapper)

}