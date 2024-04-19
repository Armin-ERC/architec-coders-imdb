package com.aerc.architectcoders.architectimdb.data

import com.aerc.architectcoders.architectimdb.data.models.RemoteMovie
import com.aerc.architectcoders.architectimdb.data.models.RemoteResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun fetchPopularMovies(@Query("region") region: String): RemoteResult

    @GET("movie/{id}")
    suspend fun fetchMovieById(@Path("id") movieId: Int): RemoteMovie
}
