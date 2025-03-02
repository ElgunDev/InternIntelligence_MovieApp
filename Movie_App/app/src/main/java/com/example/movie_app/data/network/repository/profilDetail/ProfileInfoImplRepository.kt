package com.example.movie_app.data.network.repository.profilDetail

import com.example.movie_app.data.network.models.user.UserDto
import com.example.movie_app.domain.Network.Model.User
import com.example.movie_app.domain.Network.repository.profilDetail.IProfileInfoRepository
import com.example.movie_app.presentation.utils.NetworkResource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileInfoImplRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
) :IProfileInfoRepository{
    override suspend fun getProfileInfo(uId: String): NetworkResource<User> {
        return try {
            val snapshot = fireStore.collection("users").document(uId).get().await()
            val userDto = snapshot.toObject(UserDto::class.java)
            userDto?.let {
                NetworkResource.Success(userDto.toDomain())
            }?:NetworkResource.Error("User data not found")
        }
        catch (e:Exception){
             NetworkResource.Error("Failed to fetch user data")
        }
    }

    override suspend fun updateInfo(uId: String, user: User): NetworkResource<User> {
        return try {
            fireStore.collection("users").document(uId).set(user).await()
            NetworkResource.Success(user)
        }
        catch (e:Exception){
            NetworkResource.Error("${e.message}")
        }
    }
}