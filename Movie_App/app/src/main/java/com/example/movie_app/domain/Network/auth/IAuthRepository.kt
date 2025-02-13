package com.example.movie_app.domain.Network.auth

import com.bumptech.glide.load.engine.Resource
import com.example.movie_app.presentation.utils.NetworkResource
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser

interface IAuthRepository {
        suspend fun signUp(name:String,email:String,password:String):NetworkResource<FirebaseUser?>
        suspend fun signIn(email: String,password: String):NetworkResource<FirebaseUser?>
}