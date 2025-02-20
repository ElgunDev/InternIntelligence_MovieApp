package com.example.movie_app.domain.UseCase.movies

import com.example.movie_app.data.network.models.moviesPoster.PosterResult
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetPopularUseCase @Inject constructor(
    private val movieServices: MovieServices
) {

    suspend fun getPopular():List<PosterResult>{
        return movieServices.getPopular().results
    }
}