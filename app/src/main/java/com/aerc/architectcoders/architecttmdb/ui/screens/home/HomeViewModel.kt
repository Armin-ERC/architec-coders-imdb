package com.aerc.architectcoders.architecttmdb.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aerc.architectcoders.architecttmdb.data.MoviesRepository
import com.aerc.architectcoders.architecttmdb.data.models.Movie
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val repository = MoviesRepository()

    fun onUiReady(region: String) {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                loading = false,
                movies = repository.fetchPopularMovies(region)
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList(),
    )

}
