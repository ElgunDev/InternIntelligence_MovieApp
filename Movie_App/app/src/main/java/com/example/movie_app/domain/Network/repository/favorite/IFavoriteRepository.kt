package com.example.movie_app.domain.Network.repository.favorite

import com.example.movie_app.data.network.models.detailed.MovieDetail


interface IFavoriteRepository {
    suspend fun addFavorites(movie:MovieDetail)
    suspend fun getFavorites():List<MovieDetail>
    suspend fun removeFavorites(movieId:Int)
}