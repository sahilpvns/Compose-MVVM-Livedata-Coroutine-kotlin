package com.sahilpvns.moviescompose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MoviesViewModel :ViewModel() {

    private val repository = MoviesRepository()
    val errorMessage = MutableLiveData<String>()

    val moviesList = MutableLiveData<List<Search>>()
    val detailMoviesList = MutableLiveData<DetailMoviesResponse>()


    fun fetchMovies(query: String) {
        viewModelScope.launch {
            try {
                if (repository.getMovies(query).Response == "True") {
                    moviesList.postValue(repository.getMovies(query).Search)
                } else {
                    errorMessage.postValue("No Data Found")
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }

        }
    }


    fun getDetailMovies(imdbID: String) {
        viewModelScope.launch {
            try {
                if (repository.getDetailsMovies(imdbID).Response == "True") {
                    detailMoviesList.postValue(repository.getDetailsMovies(imdbID))
                } else {
                    errorMessage.postValue("No Data Found")
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }

        }
    }

}