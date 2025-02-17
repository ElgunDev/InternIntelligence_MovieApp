package com.example.movie_app.data.network.auth

import android.widget.Toast
import com.bumptech.glide.load.engine.Resource
import com.example.movie_app.domain.Network.auth.IAuthRepository
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
    ): NetworkResource<FirebaseUser?> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            val user = result.user
            user?.let {
                val userMap = hashMapOf(
                    "uid" to it.uid,
                    "name" to name,
                    "email" to email,
                    "password" to password

                )
                firestore.collection("users").document(it.uid).set(userMap).await()
            }
            NetworkResource.Success(user)
        }
        catch (e:Exception){
            NetworkResource.Error("SignUp Failed: ${e.localizedMessage}")
        }
    }

    override suspend fun signIn(email: String, password: String): NetworkResource<FirebaseUser?> {
       return try {
           val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
           NetworkResource.Success(result.user)
       }
       catch (e:Exception){
           NetworkResource.Error("This account was not found")
       }
    }
}