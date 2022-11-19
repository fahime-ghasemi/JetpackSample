package com.example.jetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpackcompose.domain.model.Feed
import com.example.jetpackcompose.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class FeedViewModel(private val feedRepository: FeedRepository) : ViewModel() {

    val feed = feedRepository.getFeed().cachedIn(viewModelScope)

    fun search(query: String): Flow<PagingData<Feed>> {
        if (query.isEmpty()) return emptyFlow()
        return feedRepository.search(query).cachedIn(viewModelScope)
    }

}