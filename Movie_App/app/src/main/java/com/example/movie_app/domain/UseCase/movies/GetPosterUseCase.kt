package com.example.movie_app.domain.UseCase.movies

import com.example.movie_app.data.network.models.moviesPoster.PosterResult
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetPosterUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getPoster():List<PosterResult> {

        return movieServices.getPosters().results
    }

}