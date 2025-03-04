package com.example.movie_app.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.network.models.home.genres.Genre
import com.example.movie_app.data.network.models.home.movies.Movie
import com.example.movie_app.domain.UseCase.movies.home.GetGenresUseCase
import com.example.movie_app.domain.UseCase.movies.home.GetNowPlayingUseCase
import com.example.movie_app.domain.UseCase.movies.home.GetPopularUseCase
import com.example.movie_app.domain.UseCase.movies.home.GetPosterUseCase
import com.example.movie_app.domain.UseCase.movies.home.GetTopRatedUseCase
import com.example.movie_app.domain.UseCase.movies.home.GetUpComingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getPosterUseCase: GetPosterUseCase,
    private val getNowPlayingUseCase: GetNowPlayingUseCase,
    private val getPopularUseCase: GetPopularUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getUpComingUseCase: GetUpComingUseCase
):ViewModel() {

    private val _genres = MutableLiveData<List<Genre>>()
    val genres :LiveData<List<Genre>>
        get() = _genres

    private val _posters = MutableLiveData<List<Movie>>()
    val posters :LiveData<List<Movie>>
        get() = _posters

    private val _nowPlaying = MutableLiveData<List<Movie>>()
    val nowPlaying : LiveData<List<Movie>>
        get() = _nowPlaying

    private val _popular = MutableLiveData<List<Movie>>()
    val popular :LiveData<List<Movie>>
        get() = _popular

    private val _topRated = MutableLiveData<List<Movie>>()
    val topRated :LiveData<List<Movie>>
        get() = _topRated

    private val _upComing = MutableLiveData<List<Movie>>()
    val upComing :LiveData<List<Movie>>
        get() = _upComing
     fun fetchGenres(){
        viewModelScope.launch {
            try {
                _genres.value = getGenresUseCase.getGenres()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching genres", e)
            }
        }
    }

    fun fetchPosters(){
        viewModelScope.launch {
            try {
                val posters = getPosterUseCase.getPoster()
                val filteredPoster = posters.filter {
                    it.title == "Moana 2" || it.title == "Amaran"  || it.title == "Sonic the Hedgehog 3"|| it.title == "Captain America: Brave New World"
                }
                _posters.value =filteredPoster
            }
            catch (e:Exception){
                Log.e("HomeViewModel","Error fetching posters" , e)
            }
        }
    }

    fun fetchNowPlaying(){
        viewModelScope.launch {
            try {
                _nowPlaying.value = getNowPlayingUseCase.getNowPlaying()
            }
            catch (e:Exception){
                Log.e("HomeViewModel", "Error fetching Now Playing", e)
            }
        }
    }

    fun fetchPopular(){
        viewModelScope.launch {
            try {
                _popular.value = getPopularUseCase.getPopular()
            }
            catch (e:Exception){
                Log.e("HomeViewModel", "Error fetching Now Playing", e)
            }
        }
    }

    fun fetchTopRated(){
        viewModelScope.launch {
            try {
                _topRated.value = getTopRatedUseCase.getTopRated()
            }
            catch (e:Exception){
                Log.e("HomeViewModel", "Error fetching top rated", e)
            }
        }
    }

    fun fetchUpComing(){
        viewModelScope.launch {
            try {
                _upComing.value = getUpComingUseCase.getUpComing()
            }
            catch (e:Exception){
                Log.e("HomeViewModel", "Error fetching top rated", e)
            }
        }
    }
}