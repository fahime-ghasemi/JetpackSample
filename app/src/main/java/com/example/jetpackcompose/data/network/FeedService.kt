package com.example.jetpackcompose.data.network

import com.example.jetpackcompose.data.dto.FeedDto

interface FeedService {
    suspend fun getFeed(page: Int): List<FeedDto>
}