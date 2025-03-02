package com.example.movie_app.data.network.models.user

import com.example.movie_app.domain.Network.Model.User

data class UserDto(
    val uId :String = "",
    val name:String ="",
    val email:String = "",
    val password:String ="",
    val profilePhoto:String? = null,
    val fin:String?=null,
    val phoneNumber:String?=null,
    val gender:String?=null

) {
    fun toDomain(): User {
        return User(uId, name, email, password,profilePhoto, fin, phoneNumber, gender)
    }
}
