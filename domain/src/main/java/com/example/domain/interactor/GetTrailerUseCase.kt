package com.example.domain.interactor

import com.example.domain.models.video.TrailerDomain
import com.example.domain.repository.VideoRepository
import java.util.concurrent.Flow

class GetTrailerUseCase(private val repository: VideoRepository) {
//    suspend fun execute(movieId: Int, language: String): Flow<TrailerDomain> {
//        val videos = repository.getTrailers(movieId, language)
//        var trailer: TrailerDomain? = null
//        if (videos.size > 1) {
//            for (i in videos.indices) {
//                if (videos[i].isOfficial == true) {
//                    trailer = videos[i]
//                    break
//                }
//            }
//            if (trailer == null) {
//                trailer = videos[0]
//            }
//        } else {
//            trailer = videos[0]
//        }
//        return trailer
//    }
}