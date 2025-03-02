package com.example.movie_app.domain.UseCase.profile

import com.example.movie_app.data.network.repository.profilDetail.ProfileInfoImplRepository
import com.example.movie_app.domain.Network.Model.User
import com.example.movie_app.domain.Network.repository.profilDetail.IProfileInfoRepository
import com.example.movie_app.presentation.utils.NetworkResource
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileInfoImplRepository: IProfileInfoRepository) {

    suspend operator fun invoke(uId:String): NetworkResource<User> =
        profileInfoImplRepository.getProfileInfo(uId)
}