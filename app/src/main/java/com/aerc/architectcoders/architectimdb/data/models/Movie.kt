package com.aerc.architectcoders.architectimdb.data.models

data class Movie(
    val title: String,
    val poster: String
)

val movies = (1..100).map {
    Movie(
        title = "Title $it",
        poster = "https://picsum.photos/200/300?id=$it"
    )
}