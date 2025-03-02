package com.example.movie_app.data.network.repository.auth

import android.widget.Toast
import com.bumptech.glide.load.engine.Resource
import com.example.movie_app.data.network.models.user.UserDto
import com.example.movie_app.domain.Network.Model.User
import com.example.movie_app.domain.Network.repository.auth.IAuthRepository
import com.example.movie_app.presentation.utils.NetworkResource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth:FirebaseAuth,
    private val firestore: FirebaseFirestore
): IAuthRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): NetworkResource<User> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            val user = result.user
            user?.let {
                val userDto = UserDto(
                    uId = it.uid,
                    name= name,
                    email= email,
                    password= password,
                    profilePhoto = null,
                    fin = "",
                    phoneNumber = "",
                    gender = ""
                )
                firestore.collection("users").document(it.uid).set(userDto).await()
                NetworkResource.Success(userDto.toDomain())
            }?:NetworkResource.Error("User is Null")
        }
        catch (e:Exception){
            NetworkResource.Error("SignUp Failed: ${e.localizedMessage}")
        }
    }

    override suspend fun signIn(email: String, password: String): NetworkResource<String> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            user?.uid?.let { uid ->
                NetworkResource.Success(uid)
            } ?: NetworkResource.Error("Authentication failed")
        } catch (e: Exception) {
            NetworkResource.Error("This account was not found")
        }
    }
}