package com.example.movie_app.domain.UseCase.profile


import com.example.movie_app.domain.Network.Model.User
import com.example.movie_app.domain.Network.repository.profilDetail.IProfileInfoRepository
import com.example.movie_app.presentation.utils.NetworkResource
import javax.inject.Inject

class UpdateUseCase @Inject constructor(
    private val profileInfoImplRepository: IProfileInfoRepository
) {

    suspend operator fun invoke(uId:String , user: User): NetworkResource<User> =
        profileInfoImplRepository.updateInfo(uId ,user)
}