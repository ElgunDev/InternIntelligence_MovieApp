package com.example.movie_app.domain.UseCase.movies.favorite

import com.example.movie_app.data.network.repository.favorite.FavoriteImplRepository
import com.example.movie_app.domain.Network.repository.favorite.IFavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteImplRepository: IFavoriteRepository
) {
    suspend operator fun invoke(movieId:Int){
        favoriteImplRepository.removeFavorites(movieId)
    }
}