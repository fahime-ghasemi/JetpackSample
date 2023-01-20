package com.example.jetpackcompose.data.dto

import com.example.jetpackcompose.domain.model.Session
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SessionDto(
    val name: String,
    @SerialName("listener_count")
    val listenerCount: Int,
    val genres: List<String>,
    @SerialName("current_track")
    val currentTrack: TrackDto
)

fun SessionDto.toSession(): Session {
    return Session(
        name = name,
        listenerCount = listenerCount,
        genres = genres,
        currentTrack = currentTrack
    )
}
