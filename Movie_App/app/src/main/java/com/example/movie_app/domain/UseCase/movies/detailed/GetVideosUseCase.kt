package com.example.movie_app.domain.UseCase.movies.detailed

import com.example.movie_app.data.network.models.detailed.Video
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getVideos(movieId:Int):List<Video>{
        return movieServices.getVideos(movieId).results
    }
}