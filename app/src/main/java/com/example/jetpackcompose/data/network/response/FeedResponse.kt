package com.example.jetpackcompose.data.network.response

import com.example.jetpackcompose.data.dto.FeedDto
import kotlinx.serialization.Serializable

@Serializable
data class FeedResponse(val data: Data)

@Serializable
data class Data(
    val sessions: List<FeedDto>
)