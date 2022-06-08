package com.example.jetpackcompose.repository

import com.example.jetpackcompose.data.dto.FeedDto
import com.example.jetpackcompose.data.network.FeedService

class FeedRepositoryImpl(private val feedService: FeedService) : FeedRepository {

    override suspend fun getFeed(page: Int): List<FeedDto> {
        return feedService.getFeed(page)
    }
}