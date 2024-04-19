package com.aerc.architectcoders.architectimdb.ui.screens.home

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aerc.architectcoders.architectimdb.R
import com.aerc.architectcoders.architectimdb.common.PermissionRequestEffect
import com.aerc.architectcoders.architectimdb.common.getRegion
import com.aerc.architectcoders.architectimdb.data.models.Movie
import com.aerc.architectcoders.architectimdb.data.models.movies
import com.aerc.architectcoders.architectimdb.ui.screens.home.components.MovieItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClick: (Movie) -> Unit
) {

    val context = LocalContext.current
    val homeTitle  = stringResource(id = R.string.home_title)
    var appBarTitle by remember {
        mutableStateOf(homeTitle)
    }

    val coroutineScope = rememberCoroutineScope()

    PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) {granted ->
        if(granted){
            coroutineScope.launch {
                val region = context.getRegion()
                appBarTitle = "$homeTitle ($region)"
            }
        }else{
            appBarTitle = "$homeTitle (Location Permission Denied)"
        }
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = { Text(text = appBarTitle, ) },
                scrollBehavior = scrollBehavior
            )
        },
    ) { padding ->

        LazyVerticalGrid(
            modifier = Modifier.padding(padding),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            columns = GridCells.Adaptive(90.dp),
        ) {

            items(movies) { movie ->
                MovieItem(
                    movie = movie,
                    onClick = { onClick(movie) }
                )
            }

        }
    }

}
