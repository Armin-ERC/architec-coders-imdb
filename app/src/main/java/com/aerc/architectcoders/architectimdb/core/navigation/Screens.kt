package com.aerc.architectcoders.architectimdb.core.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object Detail : Screens("detail/{movieId}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}
