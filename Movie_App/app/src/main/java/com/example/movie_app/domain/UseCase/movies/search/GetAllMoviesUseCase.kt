package com.example.movie_app.domain.UseCase.movies.search

import com.example.movie_app.data.network.models.search.AllMovie
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getAllMovies():List<AllMovie>{
        return movieServices.getAllMovies().results
    }
}