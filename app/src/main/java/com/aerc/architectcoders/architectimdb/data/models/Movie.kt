package com.aerc.architectcoders.architectimdb.data.models

data class Movie(
    val id: Int,
    val title: String,
    val poster: String
)

val movies = (1..100).map {
    Movie(
        id = it,
        title = "Title $it",
        poster = "https://picsum.photos/200/300?id=$it"
    )
}