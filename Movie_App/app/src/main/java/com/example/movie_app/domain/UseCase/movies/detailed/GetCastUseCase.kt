package com.example.movie_app.domain.UseCase.movies.detailed

import com.example.movie_app.data.network.models.detailed.Cast
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetCastUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getCasts(movieId:Int):List<Cast>{
        return movieServices.getCast(movieId).cast
    }
}