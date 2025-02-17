package com.example.movie_app.domain.UseCase.movies

import com.example.movie_app.data.network.models.Genre
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getGenres(apiKey:String):List<Genre>{
       return movieServices.getGenres(apiKey).genres
    }
}