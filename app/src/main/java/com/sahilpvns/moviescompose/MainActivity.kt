package com.sahilpvns.moviescompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


class MainActivity : ComponentActivity() {
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val movies by viewModel.moviesList.observeAsState(emptyList())
            val errorMessage by viewModel.errorMessage.observeAsState("")
            MoviesScreen(movies, viewModel, errorMessage)
        }
    }

}

@Composable
fun MoviesScreen(movies: List<Search>, viewModel: MoviesViewModel, errorMessage: String) {
    Column {
        SearchMovies(viewModel)
        MovieListScreen(movies)
        Text(text = errorMessage,
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            fontWeight = FontWeight.Bold)
    }

}

@Composable
fun SearchMovies(viewModel: MoviesViewModel) {
    var search by remember { mutableStateOf("") }
    OutlinedTextField(value = search, onValueChange = {
            search = it
            viewModel.fetchMovies(search)
        },
        label = { Text(text = "Search Movies") },
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        singleLine = true
    )
}


@Composable
fun MovieListScreen(movies: List<Search>) {
    LazyVerticalGrid(GridCells.Fixed(2)){
        items(movies){
            MovieCard(it)
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieCard(movies: Search) {
    val context = LocalContext.current
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth().height(300.dp), onClick = {
        val intent = Intent(context, DetailMoviesActivity::class.java).apply {
            putExtra("imdbID", movies.imdbID)
        }
        context.startActivity(intent)
    }) {
        Column(Modifier.padding(8.dp) ){
            GlideImage(model = movies.Poster,
                contentDescription = movies.Title,
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.FillBounds)

                Text(text = movies.Title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp))
                Text(text = movies.Year)
                Text(text = movies.Type)

        }

    }


}

