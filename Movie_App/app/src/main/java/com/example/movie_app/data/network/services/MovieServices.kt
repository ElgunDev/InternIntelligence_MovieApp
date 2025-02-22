package com.example.movie_app.data.network.services

import com.example.movie_app.data.network.models.detailed.CastResponse
import com.example.movie_app.data.network.models.detailed.MovieDetail
import com.example.movie_app.data.network.models.detailed.VideoResponse
import com.example.movie_app.data.network.models.home.topRated.TopRatedResponse
import com.example.movie_app.data.network.models.home.genres.GenreResponse
import com.example.movie_app.data.network.models.home.moviesPoster.PosterResponse
import com.example.movie_app.data.network.models.home.nowPlaying.NowPlayingResponse
import com.example.movie_app.data.network.models.home.upComing.UpComingResponse
import com.example.movie_app.data.network.models.search.AllMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
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
    ): PosterResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
      @Query("page") page:Int =1,
      @Query("language") language: String = "es-US"
    ): NowPlayingResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page:Int =1,
        @Query("language") language: String ="en-US"
    ): PosterResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page:Int=1,
        @Query("language") language: String ="en-US"
    ): TopRatedResponse

    @GET("movie/upcoming")
    suspend fun getUpComing(
        @Query("page") page: Int=1,
        @Query("language") language: String="en-US"
    ): UpComingResponse

    @GET("movie/popular")
    suspend fun getAllMovies(
     @Query("page") page: Int=2,
     @Query("language")language: String= "en-US"
    ):AllMoviesResponse


    @GET("movie/{movie_id}")
    suspend fun getDetailed(
        @Path("movie_id") movieId:Int,
        @Query("language")language: String= "en-US"
    ):MovieDetail

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") movieId: Int
    ):CastResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(
        @Path("movie_id") movieId: Int
    ):VideoResponse
}