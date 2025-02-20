package com.example.movie_app.data.network.services

import com.example.movie_app.data.network.models.topRated.TopRatedResponse
import com.example.movie_app.data.network.models.genres.GenreResponse
import com.example.movie_app.data.network.models.moviesPoster.PosterResponse
import com.example.movie_app.data.network.models.nowPlaying.NowPlayingResponse
import com.example.movie_app.data.network.models.upComing.UpComingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServices {
    @GET("genre/movie/list")
    suspend fun  getGenres(
        @Query("language") language:String ="en-US"
    ): GenreResponse

    @GET("movie/popular")
    suspend fun getPosters(
        @Query("page") page:Int =1,
        @Query("language") language: String ="en-US"
    ):PosterResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
      @Query("page") page:Int =1,
      @Query("language") language: String = "es-US"
    ):NowPlayingResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page:Int =1,
        @Query("language") language: String ="en-US"
    ):PosterResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page:Int=1,
        @Query("language") language: String ="en-US"
    ):TopRatedResponse

    @GET("movie/upcoming")
    suspend fun getUpComing(
        @Query("page") page: Int=1,
        @Query("language") language: String="en-US"
    ):UpComingResponse
}