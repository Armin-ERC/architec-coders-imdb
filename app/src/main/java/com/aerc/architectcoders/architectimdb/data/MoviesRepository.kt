package com.aerc.architectcoders.architectimdb.data

import com.aerc.architectcoders.architectimdb.data.models.Movie
import com.aerc.architectcoders.architectimdb.data.models.movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MoviesRepository {

    suspend fun fetchPopularMovies() : List<Movie> = withContext(Dispatchers.IO){
        delay(2000)
        movies
    }

}
