package com.example.movie_app.data.network.dto

import com.example.movie_app.data.network.models.detailed.MovieDetail


data class MovieModelDto(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val release_date: String = "",
    val poster_path: String = "",
    val vote_average: Double = 0.0,
    val runtime: Int = 0,
    val video: Boolean = false
){
    fun toDomainModel():MovieDetail{
        return MovieDetail(
            id = id,
            title = title,
            overview = overview,
            release_date = release_date,
            poster_path = poster_path,
            genres = emptyList(),
            vote_average = vote_average,
            runtime = runtime,
            video = video

        )
    }companion object{
        fun fromDomainModel(movie:MovieDetail):MovieModelDto{
                 return MovieModelDto(
                     id = movie.id,
                     title = movie.title,
                     overview = movie.overview,
                     release_date = movie.release_date,
                     poster_path = movie.poster_path,
                     vote_average = movie.vote_average,
                     runtime = movie.runtime,
                     video = movie.video
                 )
        }
    }

}
