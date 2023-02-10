package com.example.mymovieapp.di

import com.example.data.cloud.mappers.movie.*
import com.example.data.cloud.mappers.person.*
import com.example.data.cloud.mappers.video.MapListTrailerToData
import com.example.data.cloud.mappers.video.MapTrailerCloudToData
import com.example.data.cloud.mappers.video.MapVideoCloudToData
import com.example.data.cloud.models.movie.*
import com.example.data.cloud.models.person.PersonCloud
import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.cloud.models.video.TrailerCloud
import com.example.data.cloud.models.video.VideoCloud
import com.example.data.mappers.movie.*
import com.example.data.mappers.person.*
import com.example.data.mappers.video.MapListTrailerDataToDomain
import com.example.data.models.movie.*
import com.example.data.models.person.PersonData
import com.example.data.models.person.PersonDetailsData
import com.example.data.models.person.PersonsData
import com.example.data.models.video.TrailerData
import com.example.data.models.video.VideoData
import com.example.data.storage.movie.MovieStorage
import com.example.data.storage.movie.mappers.MapListMovieStorageToData
import com.example.data.storage.movie.mappers.MapMovieDataToStorage
import com.example.data.storage.movie.mappers.MapMovieDomainToData
import com.example.data.storage.movie.mappers.MapMovieStorageToData
import com.example.data.storage.tv.mappers.MapListTvStorageToData
import com.example.data.storage.tv.mappers.MapSeriesDataToStorage
import com.example.data.storage.tv.mappers.MapTvStorageToDataMaps
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.Maps
import com.example.domain.models.movie.*
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonDomain
import com.example.domain.models.person.PersonsDomain
import com.example.domain.models.video.TrailerDomain
import com.example.domain.models.video.VideosDomain
import com.example.movieapp.data.mappers.video.MapTrailerDataToDomain
import com.example.movieapp.data.mappers.video.MapVideoDataToDomain
import com.example.mymovieapp.mappers.movie.*
import com.example.mymovieapp.mappers.person.*
import com.example.mymovieapp.models.movie.*
import com.example.mymovieapp.models.person.PersonDetailsPresentation
import com.example.mymovieapp.models.person.PersonPresentation
import com.example.mymovieapp.models.person.PersonsPresentation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {

    @Provides
    @Singleton
    fun provideMapGenresCloudToData(): Maps<GenresCloud, GenresData> = MapGenresCloudToData()

    @Provides
    @Singleton
    fun provideMapListGenresCloudToData(mapper: Maps<GenresCloud, GenresData>): Maps<List<GenresCloud>, List<GenresData>> =
        MapListGenresCloudToData(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapListCastCloudToData(mapper: Maps<CastCloud, CastData>): Maps<List<CastCloud>, List<CastData>> =
        MapListCastCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapCastCloudToData(): Maps<CastCloud, CastData> = MapCastCloudToData()

    @Provides
    @Singleton
    fun provideMapCreditsResponseCloudToData(
        mapListCastCloudToData: Maps<List<CastCloud>, List<CastData>>,
    ): Maps<CreditsResponseCloud, CreditsResponseData> = MapCreditsResponseCloudToData(
        mapListCastCloudToData = mapListCastCloudToData,
    )

    @Provides
    @Singleton
    fun provideMapListPersonDetailsCloudToData(mapper: Maps<PersonDetailsCloud, PersonDetailsData>):
            Maps<List<PersonDetailsCloud>, List<PersonDetailsData>> =
        MapListPersonDetailsCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapListCastDataToDomain(mapper: Maps<CastData, CastDomain>): Maps<List<CastData>, List<CastDomain>> =
        MapListCastDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapCastDataToDomain(): Maps<CastData, CastDomain> = MapCastDataToDomain()

    @Provides
    @Singleton
    fun provideMapCreditsResponseDataToDomain(
        mapListCastDataToDomain: Maps<List<CastData>, List<CastDomain>>,
    ): Maps<CreditsResponseData, CreditsResponseDomain> = MapCreditsResponseDataToDomain(
        mapListCastDataToDomain = mapListCastDataToDomain,
    )

    @Provides
    @Singleton
    fun provideMapListPersonDetailsDataToDomain(mapper: Maps<PersonDetailsData, PersonDetailsDomain>):
            Maps<List<PersonDetailsData>, List<PersonDetailsDomain>> =
        MapListPersonDetailsDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapListCastDomainToUi(mapper: Maps<CastDomain, CastUi>): Maps<List<CastDomain>, List<CastUi>> =
        MapListCastDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapCastDomainToUi(): Maps<CastDomain, CastUi> = MapCastDomainToUi()

    @Provides
    @Singleton
    fun provideMapCreditsResponseDomainToUi(
        mapListCastDomainToUi: Maps<List<CastDomain>, List<CastUi>>,
    ): Maps<CreditsResponseDomain, CreditsResponseUi> = MapCreditsResponseDomainToUi(
        mapListCastDomainToUi = mapListCastDomainToUi,
    )

    @Provides
    @Singleton
    fun provideMapFromMovieCloudToData(): Maps<MovieCloud, MovieData> = MapMovieCloudToData()

    @Provides
    @Singleton
    fun provideMapFromMovieDetailsCloudToData(mapper: Maps<List<GenresCloud>, List<GenresData>>): Maps<MovieDetailsCloud, MovieDetailsData> =
        MapMovieDetailsCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromMovieListCloudToData(mapper: Maps<MovieCloud, MovieData>): Maps<List<MovieCloud>, List<MovieData>> =
        MapListMovieCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromMoviesCloudToData(mapper: Maps<List<MovieCloud>, List<MovieData>>): Maps<MoviesResponseCloud, MoviesData> =
        MapMoviesCloudToData(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapFromPersonCloudListToData(mapper: Maps<PersonCloud, PersonData>): Maps<List<PersonCloud>, List<PersonData>> =
        MapListPersonCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromPersonCloudToData(mapper: Maps<List<MovieCloud>, List<MovieData>>): Maps<PersonCloud, PersonData> =
        MapPersonCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromPersonDetailsCloudToData(): Maps<PersonDetailsCloud, PersonDetailsData> =
        MapPersonDetailsCloudToData()

    @Provides
    @Singleton
    fun provideMapFromPersonsCloudToData(mapper: Maps<List<PersonCloud>, List<PersonData>>): Maps<PersonsCloud, PersonsData> =
        MapPersonsCloudToData(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapFromTrailerCloudToData(): Maps<TrailerCloud, TrailerData> =
        MapTrailerCloudToData()

    @Provides
    @Singleton
    fun provideMapFromTrailerListToData(mapper: Maps<TrailerCloud, TrailerData>): Maps<List<TrailerCloud>, List<TrailerData>> =
        MapListTrailerToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromVideoToData(videoMapper: Maps<List<TrailerCloud>, List<TrailerData>>): Maps<VideoCloud, VideoData> =
        MapVideoCloudToData(videoMapper = videoMapper)

    @Provides
    @Singleton
    fun provideMapGenresDataToDomain(): Maps<GenresData, GenresDomain> =
        MapGenresDataToDomain()

    @Provides
    @Singleton
    fun provideMapFromGenresDataToDomain(mapper: Maps<GenresData, GenresDomain>): Maps<List<GenresData>, List<GenresDomain>> =
        MapListGenresDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromMovieDataToDomain(): Maps<MovieData, MovieDomain> =
        MapMovieDataToDomain()

    @Provides
    @Singleton
    fun provideMapFromMovieDetailsDataToDomain(mapper: Maps<List<GenresData>, List<GenresDomain>>): Maps<MovieDetailsData, MovieDetailsDomain> =
        MapMovieDetailsDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromMovieListDataToDomain(mapper: Maps<MovieData, MovieDomain>): Maps<List<MovieData>, List<MovieDomain>> =
        MapListMovieDataToDomain(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapFromMoviesDataToDomain(mapper: Maps<List<MovieData>, List<MovieDomain>>): Maps<MoviesData, MoviesResponseDomain> =
        MapMoviesDataToDomain(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapFromPersonDataListToDomain(mapper: Maps<PersonData, PersonDomain>): Maps<List<PersonData>, List<PersonDomain>> =
        MapListPersonDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromPersonDataToDomain(mapper: Maps<List<MovieData>, List<MovieDomain>>): Maps<PersonData, PersonDomain> =
        MapPersonDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromPersonDetailsDataToDomain(): Maps<PersonDetailsData, PersonDetailsDomain> =
        MapPersonDetailsDataToDomain()

    @Provides
    @Singleton
    fun provideMapFromPersonsDataToDomain(mapper: Maps<List<PersonData>, List<PersonDomain>>): Maps<PersonsData, PersonsDomain> =
        MapPersonsDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromTrailerListDataToDomain(mapper: Maps<TrailerData, TrailerDomain>): Maps<List<TrailerData>, List<TrailerDomain>> =
        MapListTrailerDataToDomain(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapFromVideoDataToDomain(mapper: Maps<List<TrailerData>, List<TrailerDomain>>): Maps<VideoData, VideosDomain> =
        MapVideoDataToDomain(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapTrailerDataToDomain(): Maps<TrailerData, TrailerDomain> =
        MapTrailerDataToDomain()


    @Provides
    @Singleton
    fun provideMapFromDomainToData(): Maps<MovieDomain, MovieData> =
        MapMovieDomainToData()

    @Provides
    @Singleton
    fun provideMapMovieFromStorageToData(): Maps<MovieStorage, MovieData> =
        MapMovieStorageToData()


    @Provides
    @Singleton
    fun provideMapMovieListToDomain(mapper: Maps<MovieStorage, MovieData>): Maps<List<MovieStorage>, List<MovieData>> =
        MapListMovieStorageToData(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapMovieToStorage(): Maps<MovieData, MovieStorage> =
        MapMovieDataToStorage()

    @Provides
    @Singleton
    fun provideMapSeriesDataToStorage(): Maps<SeriesData, TvStorage> = MapSeriesDataToStorage()

    @Provides
    @Singleton
    fun provideMapTvStorageToDataMaps(): Maps<TvStorage, SeriesData> = MapTvStorageToDataMaps()

    @Provides
    @Singleton
    fun provideMapListTvStorageToData(mapper: Maps<TvStorage, SeriesData>):
            Maps<List<TvStorage>, List<SeriesData>> = MapListTvStorageToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromMovieDomainToPresentation(): Maps<MovieDomain, MovieUi> =
        MapMovieDomainToUi()

    @Provides
    @Singleton
    fun provideMapFromMovieListDomainToPresentation(mapper: Maps<MovieDomain, MovieUi>): Maps<List<MovieDomain>, List<MovieUi>> =
        MapListMovieDomainToUi(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapFromMoviesDomainToPresentation(mapper: Maps<List<MovieDomain>, List<MovieUi>>): Maps<MoviesResponseDomain, MoviesResponseUi> =
        MapMoviesDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapMovieUiToDomain(): Maps<MovieUi, MovieDomain> = MapMovieUiToDomain()

    @Provides
    @Singleton
    fun provideMapFromPersonDomainListToPresentation(mapper: Maps<PersonDomain, PersonPresentation>): Maps<List<PersonDomain>, List<PersonPresentation>> =
        MapListPersonDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapFromPersonDomainToPresentation(mapper: Maps<List<MovieDomain>, List<MovieUi>>): Maps<PersonDomain, PersonPresentation> =
        MapPersonDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapPersonsDomainToUi(mapper: Maps<List<PersonDomain>, List<PersonPresentation>>): Maps<PersonsDomain, PersonsPresentation> =
        MapPersonsDomainToUi(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapGenresDomainToUi(): Maps<GenresDomain, MovieGenresPresentation> =
        MapGenresDomainToUi()

    @Provides
    @Singleton
    fun provideMapListMovieGenresDomainToUi(mapper: Maps<GenresDomain, MovieGenresPresentation>): Maps<List<GenresDomain>, List<MovieGenresPresentation>> =
        MapListMovieGenresDomainToUi(mapper = mapper)


    @Provides
    @Singleton
    fun provideMapMovieDetailsDomainToUi(mapper: Maps<List<GenresDomain>, List<MovieGenresPresentation>>): Maps<MovieDetailsDomain, MovieDetailsUi> =
        MapMovieDetailsDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapPersonDetailsDomainToUi(): Maps<PersonDetailsDomain, PersonDetailsPresentation> =
        MapPersonDetailsDomainToUi()

    @Provides
    @Singleton
    fun provideMapListPersonDetailsDomainToUi(mapper: Maps<PersonDetailsDomain, PersonDetailsPresentation>): Maps<List<PersonDetailsDomain>, List<PersonDetailsPresentation>> =
        MapListPersonDetailsDomainToUi(mapper = mapper)


    // Tv Movies Mappers Module
    @Provides
    @Singleton
    fun provideMapListSeriesCloudToData(mapper: Maps<SeriesCloud, SeriesData>): Maps<List<SeriesCloud>, List<SeriesData>> =
        MapListSeriesCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapSeriesCloudToData(): Maps<SeriesCloud, SeriesData> =
        MapSeriesCloudToData()

    @Provides
    @Singleton
    fun provideMapTvResponseCloudData(mapper: Maps<List<SeriesCloud>, List<SeriesData>>): Maps<TvSeriesResponseCloud, TvSeriesResponseData> =
        MapTvResponseCloudData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapListSeriesDataToDomain(mapper: Maps<SeriesData, SeriesDomain>): Maps<List<SeriesData>, List<SeriesDomain>> =
        MapListSeriesDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapSeriesDataToDomain(): Maps<SeriesData, SeriesDomain> =
        MapSeriesDataToDomain()

    @Provides
    @Singleton
    fun provideMapSeriesDomainToData(): Maps<SeriesDomain, SeriesData> =
        MapSeriesDomainToData()

    @Provides
    @Singleton
    fun provideMapTvResponseDataToDomain(mapper: Maps<List<SeriesData>, List<SeriesDomain>>): Maps<TvSeriesResponseData, TvSeriesResponseDomain> =
        MapTvResponseDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapListSeriesDomainToUi(mapper: Maps<SeriesDomain, SeriesUi>): Maps<List<SeriesDomain>, List<SeriesUi>> =
        MapListSeriesDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapSeriesDomainToUi(): Maps<SeriesDomain, SeriesUi> =
        MapSeriesDomainToUi()

    @Provides
    @Singleton
    fun provideMapSeriesUiToDomain(): Maps<SeriesUi, SeriesDomain> =
        MapSeriesUiToDomain()

    @Provides
    @Singleton
    fun provideMapTvResponseDomainToUi(mapper: Maps<List<SeriesDomain>, List<SeriesUi>>): Maps<TvSeriesResponseDomain, TvSeriesResponseUi> =
        MapTvResponseDomainToUi(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapTvDetailsCloudToData(mapper: Maps<List<GenresCloud>, List<GenresData>>): Maps<TvSeriesDetailsCloud, TvSeriesDetailsData> =
        MapTvDetailsCloudToData(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapTvDetailsDataToDomain(mapper: Maps<List<GenresData>, List<GenresDomain>>): Maps<TvSeriesDetailsData, TvSeriesDetailsDomain> =
        MapTvDetailsDataToDomain(mapper = mapper)

    @Provides
    @Singleton
    fun provideMapTvSeriesDetailsDomainToUi(mapper: Maps<List<GenresDomain>, List<MovieGenresPresentation>>):
            Maps<TvSeriesDetailsDomain, TvSeriesDetailsUi> =
        MapTvSeriesDetailsDomainToUi(mapper = mapper)


}