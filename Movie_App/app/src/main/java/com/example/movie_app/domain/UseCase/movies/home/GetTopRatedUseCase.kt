package com.example.movie_app.domain.UseCase.movies.home

import com.example.movie_app.data.network.models.home.movies.Movie
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getTopRated():List<Movie>{
        return movieServices.getTopRated().results
    }
}