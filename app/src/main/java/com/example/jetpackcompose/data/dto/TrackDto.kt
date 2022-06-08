package com.example.jetpackcompose.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackDto(
    val title: String,
    @SerialName("artwork_url")
    val artwork: String
)
