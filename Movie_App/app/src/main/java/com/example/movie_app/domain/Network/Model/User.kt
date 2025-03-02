package com.example.movie_app.domain.Network.Model

data class User(
val uId:String,
val name:String,
val email:String,
val password:String,
val profilePhoto:String?=null,
    val fin:String?=null,
    val phoneNumber:String?=null,
    val gender:String?=null

)

