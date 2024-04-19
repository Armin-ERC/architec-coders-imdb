package com.aerc.architectcoders.architecttmdb.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aerc.architectcoders.architecttmdb.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    vm: DetailViewModel,
    onBackClick: () -> Unit
) {

    val state = vm.state

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = { Text(text = state.movie?.title ?: "") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.navigation_back
                            )
                        )
                    }
                }
            )
        },
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            if (state.loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.movie?.let {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f),
                    model = it.poster,
                    contentDescription = it.title,
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = it.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = it.overview,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}
