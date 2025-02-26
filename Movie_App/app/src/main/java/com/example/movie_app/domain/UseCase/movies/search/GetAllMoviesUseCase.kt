package com.example.movie_app.domain.UseCase.movies.search

import com.example.movie_app.data.network.models.home.movies.Movie

import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getAllMovies():List<Movie>{
        return movieServices.getAllMovies().results
    }
}