package com.example.movie_app.data.network.services

import com.example.movie_app.data.network.models.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServices {
    @GET("/genre/movie/list")
    suspend fun  getGenres(
        @Query("apiKey") apiKey:String,
        @Query("language") language:String ="en-US"
    ):GenreResponse
}