package com.sahilpvns.moviescompose

class MoviesRepository {
    private val apiService = RetrofitClient.api.create(MoviesInterface::class.java)

    suspend fun getMovies(query: String): MoviesResponse {
        return apiService.getMovies(query = query)
    }

    suspend fun getDetailsMovies(imdbID: String): DetailMoviesResponse {
        return apiService.getDetailsMovies(imdbID = imdbID)
    }


}