package com.example.movie_app.domain.UseCase.movies.favorite

import com.example.movie_app.data.network.models.detailed.MovieDetail
import com.example.movie_app.data.network.repository.favorite.FavoriteImplRepository
import com.example.movie_app.domain.Network.repository.favorite.IFavoriteRepository
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val favoriteImplRepository: IFavoriteRepository
) {
    suspend operator fun invoke(movie:MovieDetail){
        favoriteImplRepository.addFavorites(movie)
    }
}