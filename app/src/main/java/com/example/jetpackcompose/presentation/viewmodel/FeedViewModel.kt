package com.example.jetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpackcompose.data.network.FeedPagingSource
import com.example.jetpackcompose.domain.model.Feed
import com.example.jetpackcompose.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class FeedViewModel(private val feedRepository: FeedRepository) : ViewModel() {

    fun getFeedResultStream(): Flow<PagingData<Feed>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FeedPagingSource(feedRepository) }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}