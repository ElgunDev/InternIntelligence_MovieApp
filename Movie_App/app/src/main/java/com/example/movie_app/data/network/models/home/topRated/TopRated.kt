package com.example.movie_app.data.network.models.home.topRated

data class TopRated(
    val id :Int,
    val title:String,
    val poster_path:String?,
    val genre_ids: List<Int>
)
