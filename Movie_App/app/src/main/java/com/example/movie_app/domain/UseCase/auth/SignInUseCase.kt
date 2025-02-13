package com.example.movie_app.domain.UseCase.auth


import com.example.movie_app.domain.Network.auth.IAuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepositoryImpl: IAuthRepository) {
    suspend operator fun invoke(email:String , password:String)=
        authRepositoryImpl.signIn(email,password)

}