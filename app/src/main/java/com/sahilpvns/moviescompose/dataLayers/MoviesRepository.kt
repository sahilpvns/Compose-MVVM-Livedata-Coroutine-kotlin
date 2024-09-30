package com.sahilpvns.moviescompose.dataLayers

import com.sahilpvns.moviescompose.domainLayers.RetrofitClient
import retrofit2.Response

class MoviesRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getMovies(query: String): Response<MoviesResponse>{
        return apiService.getMovies(query = query)
    }

    suspend fun getDetailsMovies(imdbID: String): Response<DetailMoviesResponse> {
        return apiService.getDetailsMovies(imdbID = imdbID)
    }


}