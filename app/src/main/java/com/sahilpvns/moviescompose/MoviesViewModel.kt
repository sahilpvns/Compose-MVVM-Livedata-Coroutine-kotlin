package com.sahilpvns.moviescompose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val repository = MoviesRepository()
    private val errorMessage = MutableLiveData<String>()

    private val _moviesList = MutableLiveData<List<Search>>()
    val moviesList: LiveData<List<Search>> = _moviesList

    fun fetchMovies(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovies(query)
                if (response.Response == "True") {
                    _moviesList.postValue(response.Search)
                } else {
                    errorMessage.postValue(response.Response)
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }

        }
    }


    private val _detailMoviesList = MutableLiveData<DetailMoviesResponse>()
    val detailMoviesList: LiveData<DetailMoviesResponse> = _detailMoviesList

    fun getDetailMovies(imdbID: String) {
        viewModelScope.launch {
            try {
                val response = repository.getDetailsMovies(imdbID)
                if (response.Response == "True") {
                    _detailMoviesList.postValue(response)
                } else {
                    errorMessage.postValue(response.Response)
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }

        }
    }

}