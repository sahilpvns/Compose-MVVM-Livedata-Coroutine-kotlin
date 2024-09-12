package com.sahilpvns.moviescompose

data class MoviesResponse(
    val Search: List<Search>,
    val totalResults: String,
    val Response: String
)

data class Search(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)
