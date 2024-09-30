package com.sahilpvns.moviescompose.dataLayers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahilpvns.moviescompose.domainLayers.NetworkResponse
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val repository = MoviesRepository()

    private val _moviesList = MutableLiveData<NetworkResponse<List<Search>>>()
    val moviesList: LiveData<NetworkResponse<List<Search>>> = _moviesList

    fun fetchMovies(query: String) {
        _moviesList.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = repository.getMovies(query)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _moviesList.value = NetworkResponse.Success(it.Search)
                    }
                } else {
                    _moviesList.value = NetworkResponse.Error("No movies found")
                }
            } catch (e: Exception) {
                _moviesList.value = NetworkResponse.Error(e.message ?: "An error occurred")
            }

        }
    }


    private val _detailMoviesList = MutableLiveData<NetworkResponse<DetailMoviesResponse>>()
    val detailMoviesList: LiveData<NetworkResponse<DetailMoviesResponse>> = _detailMoviesList

    fun getDetailMovies(imdbID: String) {
        viewModelScope.launch {
            try {
                val response = repository.getDetailsMovies(imdbID)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _detailMoviesList.value = NetworkResponse.Success(it)
                    }
                } else {
                    _detailMoviesList.value = NetworkResponse.Error("No movies found")
                }
            } catch (e: Exception) {
                _detailMoviesList.value = NetworkResponse.Error(e.message ?: "An error occurred")
            }

        }
    }

}