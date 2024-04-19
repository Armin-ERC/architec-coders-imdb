package com.aerc.architectcoders.architecttmdb.ui.screens.home.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aerc.architectcoders.architecttmdb.data.models.Movie

@Composable
fun MovieItem(
    movie: Movie,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small),
            model = movie.poster,
            contentDescription = movie.title
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = movie.title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
        )
    }
}
