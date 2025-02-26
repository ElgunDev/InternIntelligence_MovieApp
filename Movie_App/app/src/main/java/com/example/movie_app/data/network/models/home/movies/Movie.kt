package com.example.movie_app.data.network.models.home.movies

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val genre_ids: List<Int>
)
