package com.example.movie_app.domain.Network.repository.profilDetail

import com.example.movie_app.domain.Network.Model.User
import com.example.movie_app.presentation.utils.NetworkResource

interface IProfileInfoRepository {

    suspend fun getProfileInfo(uId:String):NetworkResource<User>

    suspend fun updateInfo(uId: String , user: User):NetworkResource<User>
}