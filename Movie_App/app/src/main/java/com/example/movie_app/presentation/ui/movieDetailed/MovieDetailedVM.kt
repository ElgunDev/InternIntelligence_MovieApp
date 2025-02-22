package com.example.movie_app.presentation.ui.movieDetailed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.network.models.detailed.Cast
import com.example.movie_app.data.network.models.detailed.MovieDetail
import com.example.movie_app.domain.UseCase.movies.detailed.GetCastUseCase
import com.example.movie_app.domain.UseCase.movies.detailed.GetDetailedUseCase
import com.example.movie_app.domain.UseCase.movies.detailed.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailedVM @Inject constructor(
    private val getDetailedUseCase: GetDetailedUseCase,
    private val getCastUseCase: GetCastUseCase,
    private val getVideosUseCase: GetVideosUseCase
):ViewModel() {

    private val _movieDetailed = MutableLiveData<MovieDetail>()
    val movieDetailed :LiveData<MovieDetail>
        get() = _movieDetailed

    private val _casts = MutableLiveData<List<Cast>>()
    val cast : LiveData<List<Cast>>
        get() = _casts

    private val _trailerKey = MutableLiveData<String>()
    val trailerKey :LiveData<String>
        get() = _trailerKey
    fun fetchMovieDetailed(movieId:Int){
        viewModelScope.launch {
            try {
                _movieDetailed.value = getDetailedUseCase.getDetailed(movieId)
            }
            catch (e:Exception){
                Log.e("MovieDetailedVM" , "Error fetching Detailed")
            }
        }
    }

    fun fetchCast(movieId: Int){
        viewModelScope.launch(){
            try {
                _casts.value = getCastUseCase.getCasts(movieId)
            }
            catch (e:Exception){}
            Log.e("MovieDetailedVM" , "Error fetching Casts")
        }
    }

    fun loadMovieTrailer(movieId: Int){
        viewModelScope.launch {
            try {
                val response = getVideosUseCase.getVideos(movieId)
                val trailer = response?.find {
                    it.site =="YouTube" && it.type=="Trailer" }
                trailer?.key?.let {
                    _trailerKey.postValue(it)
                }
            }
            catch (e:Exception){
                Log.e("MovieDetailedVM" , "Error fetching Trailer")
            }
        }
    }
}