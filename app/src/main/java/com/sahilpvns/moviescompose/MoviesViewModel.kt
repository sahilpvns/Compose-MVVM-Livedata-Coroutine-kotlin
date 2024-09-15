package com.sahilpvns.moviescompose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val repository = MoviesRepository()
    val errorMessage = MutableLiveData<String>()

    val moviesList = MutableLiveData<List<Search>>()

    fun fetchMovies(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovies(query)
                if (response.Response == "True") {
                    moviesList.postValue(response.Search)
                } else {
                    errorMessage.postValue(response.Response)
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }

        }
    }


    val detailMoviesList = MutableLiveData<DetailMoviesResponse>()

    fun getDetailMovies(imdbID: String) {
        viewModelScope.launch {
            try {
                val response = repository.getDetailsMovies(imdbID)
                if (response.Response == "True") {
                    detailMoviesList.postValue(response)
                } else {
                    errorMessage.postValue(response.Response)
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }

        }
    }

}