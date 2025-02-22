package com.example.movie_app.domain.UseCase.movies.detailed

import com.example.movie_app.data.network.models.detailed.MovieDetail
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetDetailedUseCase @Inject constructor(
    private val movieServices: MovieServices
) {
    suspend fun getDetailed(movieId:Int):MovieDetail{
        return movieServices.getDetailed(movieId)
    }

}