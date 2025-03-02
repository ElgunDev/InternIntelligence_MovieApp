package com.example.movie_app.domain.UseCase.auth


import com.example.movie_app.domain.Network.Model.User
import com.example.movie_app.domain.Network.repository.auth.IAuthRepository
import com.example.movie_app.presentation.utils.NetworkResource
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepositoryImpl: IAuthRepository) {
    suspend operator fun invoke(email:String , password:String):NetworkResource<String> =
        authRepositoryImpl.signIn(email,password)

}