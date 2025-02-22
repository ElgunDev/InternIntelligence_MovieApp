package com.example.movie_app.data.network.models.detailed

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String,
    val genres: List<Genres>,
    val vote_average: Double,
    val runtime: Int,
    val video:Boolean
)
