package com.aerc.architectcoders.architecttmdb.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aerc.architectcoders.architecttmdb.data.MoviesRepository
import com.aerc.architectcoders.architecttmdb.data.models.Movie
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: Int
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val repository = MoviesRepository()

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                loading = false,
                movie = repository.findMovieById(movieId = id)
            )
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null
    )

}
