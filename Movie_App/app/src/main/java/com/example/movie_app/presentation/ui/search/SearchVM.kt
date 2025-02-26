package com.example.movie_app.presentation.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.network.models.home.movies.Movie

import com.example.movie_app.domain.UseCase.movies.search.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase
):ViewModel() {

    private val _allMovie = MutableLiveData<List<Movie>>()
    val allMovie : MutableLiveData<List<Movie>>
        get() = _allMovie

    private val _filteredMovies = MutableLiveData<List<Movie>>()
    val filteredMovies :MutableLiveData<List<Movie>>
        get() = _filteredMovies

    fun fetchAllMovie(){
        viewModelScope.launch {
            try {
                _allMovie.value = getAllMoviesUseCase.getAllMovies()
                _filteredMovies.value = getAllMoviesUseCase.getAllMovies()
            }
            catch (e:Exception){
                Log.e("SearchViewModel", "Error fetching all Movies", e)
            }
        }
    }
    fun fetchFilteredMovies(query:String){
        val filteredList = _allMovie.value?.filter {movie->
            movie.title.contains(query,ignoreCase = true)
        }
            _filteredMovies.value = filteredList!!

    }
}