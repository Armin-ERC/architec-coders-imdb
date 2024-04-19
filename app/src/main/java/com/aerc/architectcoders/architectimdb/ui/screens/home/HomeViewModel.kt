package com.aerc.architectcoders.architectimdb.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aerc.architectcoders.architectimdb.data.models.Movie
import com.aerc.architectcoders.architectimdb.data.models.movies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(){

    var state by mutableStateOf(UiState())
        private set

    fun onUiReady(){
        viewModelScope.launch {
            state = UiState(loading = true)
            delay(2000)
            state = UiState(
                loading = false,
                movies = movies
            )
        }
    }

    data class UiState(
        val loading : Boolean = false,
        val movies : List<Movie> = emptyList(),
    )

}
