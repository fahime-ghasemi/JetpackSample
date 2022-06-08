package com.example.jetpackcompose.repository
import com.example.jetpackcompose.data.dto.FeedDto

interface FeedRepository {
    suspend fun getFeed(page:Int): List<FeedDto>
}