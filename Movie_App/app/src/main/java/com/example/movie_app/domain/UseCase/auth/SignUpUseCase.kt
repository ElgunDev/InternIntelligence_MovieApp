package com.example.movie_app.domain.UseCase.auth

import com.example.movie_app.data.network.auth.AuthRepositoryImpl
import com.example.movie_app.domain.Network.auth.IAuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepositoryImpl: IAuthRepository) {
    suspend operator fun invoke(name:String , email:String ,password:String)
    =    authRepositoryImpl.signUp(name,email,password)

}