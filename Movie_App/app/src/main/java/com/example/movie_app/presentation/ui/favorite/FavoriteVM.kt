package com.example.movie_app.presentation.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.network.dto.MovieModelDto
import com.example.movie_app.data.network.models.detailed.MovieDetail
import com.example.movie_app.domain.UseCase.movies.favorite.DeleteFavoriteUseCase
import com.example.movie_app.domain.UseCase.movies.favorite.GetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVM @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
):ViewModel() {

    private val _favoriteMovie = MutableLiveData<List<MovieDetail>> ()
    val favoriteMovie: LiveData<List<MovieDetail>>
        get() = _favoriteMovie

    fun fetchFavoriteMovie(){
        viewModelScope.launch {
            try {
                _favoriteMovie.value = getFavoriteUseCase.invoke()
            }
            catch (e:Exception){
                Log.e("FavoriteViewModel", "Error fetching favorite Movies", e)
            }
        }
    }
    fun deleteMovie(movieId:Int){
        viewModelScope.launch {
            deleteFavoriteUseCase.invoke(movieId)
            fetchFavoriteMovie()
        }
    }
}