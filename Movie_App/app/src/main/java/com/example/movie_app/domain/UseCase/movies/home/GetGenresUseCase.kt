package com.example.movie_app.domain.UseCase.movies.home

import com.example.movie_app.data.network.models.home.genres.Genre
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getGenres():List<Genre>{
       return movieServices.getGenres().genres
    }
}