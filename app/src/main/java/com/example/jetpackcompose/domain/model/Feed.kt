package com.example.jetpackcompose.domain.model
import com.example.jetpackcompose.data.dto.TrackDto

data class Feed (
    val name: String,
    val listenerCount: Int,
    val genres: List<String>,
    val currentTrack: TrackDto
)
{
//   val previewFeedResource = listOf(
//       Feed()
//   )
}