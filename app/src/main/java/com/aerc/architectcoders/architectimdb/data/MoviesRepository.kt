package com.aerc.architectcoders.architectimdb.data

import com.aerc.architectcoders.architectimdb.data.models.Movie
import com.aerc.architectcoders.architectimdb.data.models.toDomainModel

class MoviesRepository {
    suspend fun fetchPopularMovies(region: String): List<Movie> =
        MoviesClient
            .instance
            .fetchPopularMovies(region)
            .results
            .map {
                it.toDomainModel()
            }
}
