package com.example.movie_app.domain.UseCase.movies.home

import com.example.movie_app.data.network.models.home.movies.Movie

import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(
    private val movieServices: MovieServices
) {

    suspend fun getNowPlaying():List<Movie>{
        return movieServices.getNowPlaying().results
    }
}