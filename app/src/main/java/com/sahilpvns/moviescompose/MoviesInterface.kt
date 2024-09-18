package com.sahilpvns.moviescompose

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesInterface {

    // https://www.omdbapi.com/?apikey=b87de33c&s=jawan&page=1

    @GET("/")
    suspend fun getMovies(
        @Query("apikey") apiKey: String = "b87de33c",
        @Query("s") query: String,
        @Query("page") page: Int = 1) : MoviesResponse


    // http://www.omdbapi.com/?i=tt15354916&apikey=b87de33c

    @GET("/")
    suspend fun getDetailsMovies(
        @Query("i") imdbID: String,
        @Query("apikey") apiKey: String = "b87de33c"): DetailMoviesResponse

}