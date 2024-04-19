package com.aerc.architectcoders.architectimdb.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aerc.architectcoders.architectimdb.ui.screens.detail.DetailScreen
import com.aerc.architectcoders.architectimdb.ui.screens.detail.DetailViewModel
import com.aerc.architectcoders.architectimdb.ui.screens.home.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(Screens.Detail.createRoute(movie.id))
                }
            )
        }
        composable(
            Screens.Detail.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->

            val movieId = requireNotNull(backStackEntry.arguments?.getInt("movieId"))

            DetailScreen(
                vm = viewModel { DetailViewModel(movieId) },
                onBackClick = { navController.popBackStack() }
            )
        }
    }

}
