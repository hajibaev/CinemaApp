package com.example.data.cloud.utils

object Endpoints {
    const val LANGUAGE = "en"

    object Movie {
        //         MOVIE
        const val NOW_PLAIN = "movie/now_playing"
        const val POPULAR = "movie/popular"
        const val TOP_RATED = "movie/top_rated"
        const val UPCOMING = "movie/upcoming"
        const val TRENDING = "trending/movie/week"

        //           SEARCH
        const val SEARCH_MOVIE = "search/movie"

        //            DETAILS
        const val MOVIE_DETAILS = "movie/{movie_id}"
        const val SIMILAR = "movie/{movie_id}/similar"
        const val RECOMMENDATIONS = "movie/{movie_id}/recommendations"

        //         TV
        const val TRENDING_TV = "trending/tv/day"
        const val TOP_RATED_TV = "tv/top_rated"
        const val ON_THE_AIR_TV = "tv/on_the_air"
        const val POPULAR_TV = "tv/popular"
        const val AIRING_TODAY_TV = "tv/airing_today"
    }

    object Person {
        const val PEOPLE = "person/popular"
        const val SEARCH_PEOPLE = "search/person"
        const val PERSON_DETAILS = "person/{person_id}"
    }

}