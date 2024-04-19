package com.aerc.architectcoders.architecttmdb.ui.screens.home

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aerc.architectcoders.architecttmdb.R
import com.aerc.architectcoders.architecttmdb.common.PermissionRequestEffect
import com.aerc.architectcoders.architecttmdb.common.getRegion
import com.aerc.architectcoders.architecttmdb.data.models.Movie
import com.aerc.architectcoders.architecttmdb.ui.screens.home.components.MovieItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: HomeViewModel = viewModel(),
    onMovieClick: (Movie) -> Unit
) {

    val state = vm.state
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
        coroutineScope.launch {
            val region = if (granted) context.getRegion() else "US"
            vm.onUiReady(region)
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.home_title)) },
                scrollBehavior = scrollBehavior
            )
        },
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (state.loading) {
                CircularProgressIndicator()
            }

            LazyVerticalGrid(
                modifier = Modifier
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                columns = GridCells.Adaptive(90.dp),
            ) {
                items(state.movies) { movie ->
                    MovieItem(
                        movie = movie,
                        onClick = { onMovieClick(movie) }
                    )
                }
            }

        }
    }

}
