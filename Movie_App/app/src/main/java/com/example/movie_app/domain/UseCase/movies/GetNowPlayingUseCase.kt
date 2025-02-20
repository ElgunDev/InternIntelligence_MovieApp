package com.example.movie_app.domain.UseCase.movies

import com.example.movie_app.data.network.models.nowPlaying.NowPlaying
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(
    private val movieServices: MovieServices
) {

    suspend fun getNowPlaying():List<NowPlaying>{
        return movieServices.getNowPlaying().results
    }
}