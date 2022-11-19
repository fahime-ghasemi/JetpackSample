package com.example.jetpackcompose.repository
import androidx.paging.PagingData
import com.example.jetpackcompose.domain.model.Feed
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    fun getFeed(): Flow<PagingData<Feed>>
    fun search(query:String):Flow<PagingData<Feed>>
}