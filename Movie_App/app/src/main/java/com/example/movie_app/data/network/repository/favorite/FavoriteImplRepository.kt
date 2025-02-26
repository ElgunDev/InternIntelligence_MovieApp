package com.example.movie_app.data.network.repository.favorite

import com.example.movie_app.data.network.dto.MovieModelDto
import com.example.movie_app.data.network.models.detailed.MovieDetail

import com.example.movie_app.domain.Network.repository.favorite.IFavoriteRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FavoriteImplRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val auth: FirebaseAuth
):IFavoriteRepository {
    override suspend fun addFavorites(movie: MovieDetail) {
          val userId = auth.currentUser?.uid?: return
        val favoriteRef = firebaseFirestore.collection("users").document(userId).collection("favorites").document(movie.id.toString())
        favoriteRef.set(MovieModelDto.fromDomainModel(movie)).await()
    }

    override suspend fun getFavorites(): List<MovieDetail> {
     val userId = auth.currentUser?.uid?:return emptyList()
        val favoriteRef = firebaseFirestore.collection("users").document(userId).collection("favorites")
        return try {
            val snapshot = favoriteRef.get().await()
            snapshot.documents.mapNotNull {
                it.toObject(MovieModelDto::class.java)?.toDomainModel()
            }
        }
       catch (e:Exception){
           e.printStackTrace()
           emptyList()
       }
    }

    override suspend fun removeFavorites(movieId: Int) {
        var userId = auth.currentUser?.uid?: return
        firebaseFirestore.collection("users").document(userId).collection("favorites").document(movieId.toString()).delete().await()
    }
}