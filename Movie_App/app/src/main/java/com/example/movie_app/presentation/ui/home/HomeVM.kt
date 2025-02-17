package com.example.movie_app.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.network.models.Genre
import com.example.movie_app.domain.UseCase.movies.GetGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getGenresUseCase:GetGenresUseCase
):ViewModel() {

    private val _genres = MutableLiveData<List<Genre>>()
    val genres :LiveData<List<Genre>>
        get() = _genres


    private fun fetchGenres(apiKey:String){
        viewModelScope.launch {
            try {
                _genres.value = getGenresUseCase.getGenres(apiKey)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching genres", e)
            }
        }
    }
}