package com.example.jetpackcompose.data.dto

import com.example.jetpackcompose.domain.model.Feed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedDto(
    val name: String,
    @SerialName("listener_count")
    val listenerCount: Int,
    val genres: List<String>,
    @SerialName("current_track")
    val currentTrack: TrackDto
)

fun FeedDto.toFeed(): Feed {
    return Feed(
        name = name,
        listenerCount = listenerCount,
        genres = genres,
        currentTrack = currentTrack
    )
}
