package com.example.movie_app.domain.UseCase.movies

import com.example.movie_app.data.network.models.upComing.UpComing
import com.example.movie_app.data.network.services.MovieServices
import javax.inject.Inject

class GetUpComingUseCase @Inject constructor(
    private val movieServices: MovieServices
) {

    suspend fun getUpComing():List<UpComing>{
        return movieServices.getUpComing().results
    }
}