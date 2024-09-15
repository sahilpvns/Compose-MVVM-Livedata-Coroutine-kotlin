package com.sahilpvns.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

class DetailMoviesActivity : ComponentActivity() {
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val imdbID = intent.getStringExtra("imdbID")
            viewModel.getDetailMovies(imdbID?: "")

            val getDetailMovies by viewModel.detailMoviesList.observeAsState()
            MoviesDetailLayout(getDetailMovies)
        }
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
 fun MoviesDetailLayout(movies: DetailMoviesResponse?) {
     Column (modifier = Modifier.padding(16.dp)){
         GlideImage(model = movies?.Poster, contentDescription = movies?.Title,
             modifier = Modifier.height(250.dp),
             alignment = Alignment.TopCenter)

         Text(text = movies?.Title ?: "",
             modifier = Modifier.padding(top = 16.dp),
             fontWeight = FontWeight.Bold)

         Text(text = movies?.Year ?: "")
         Text(text = movies?.Rated ?: "")
         Text(text = movies?.Released ?: "")
         Text(text = movies?.Runtime ?: "")
         Text(text = movies?.Genre ?: "")
         Text(text = movies?.Director ?: "")
         Text(text = movies?.Writer ?: "")
         Text(text = movies?.Actors ?: "")
         Text(text = movies?.Plot ?: "")
         Text(text = movies?.Language ?: "")
         Text(text = movies?.Country ?: "")
         Text(text = movies?.Awards ?: "")
     }
}
