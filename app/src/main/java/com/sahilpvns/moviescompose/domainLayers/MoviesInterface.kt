package com.sahilpvns.moviescompose.domainLayers

import com.sahilpvns.moviescompose.dataLayers.DetailMoviesResponse
import com.sahilpvns.moviescompose.dataLayers.MoviesResponse
import com.sahilpvns.moviescompose.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesInterface {

    // https://www.omdbapi.com/?apikey=b87de33c&s=jawan&page=1

    @GET("/")
    suspend fun getMovies(
    @Query("apikey") apiKey: String = API_KEY,
        @Query("s") query: String,
        @Query("page") page: Int = 1
    ): Response<MoviesResponse>


    // http://www.omdbapi.com/?i=tt15354916&apikey=b87de33c

    @GET("/")
    suspend fun getDetailsMovies(
        @Query("i") imdbID: String,
        @Query("apikey") apiKey: String = API_KEY
    ): Response<DetailMoviesResponse>

}