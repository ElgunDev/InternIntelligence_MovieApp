package com.example.movie_app.domain.UseCase.auth

import com.example.movie_app.data.network.repository.auth.AuthRepositoryImpl
import com.example.movie_app.domain.Network.repository.auth.IAuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepositoryImpl: IAuthRepository) {
    suspend operator fun invoke(name:String , email:String ,password:String,profileImage:String)
    =    authRepositoryImpl.signUp(name,email,password,profileImage)

}