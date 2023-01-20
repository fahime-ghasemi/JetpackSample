package com.example.jetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.jetpackcompose.data.network.FeedPagingSource
import com.example.jetpackcompose.domain.interactor.GetSessionListUseCase
import com.example.jetpackcompose.domain.interactor.SearchSessionListUseCase

class SessionsViewModel(
    private val getSessionListUseCase: GetSessionListUseCase,
    private val searchSessionListUseCase: SearchSessionListUseCase
) : ViewModel() {

    val feed =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FeedPagingSource(
                    getSessionListUseCase,
                    searchSessionListUseCase,
                    null
                )
            }
        ).flow.cachedIn(viewModelScope)


    fun search(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FeedPagingSource(
                    getSessionListUseCase,
                    searchSessionListUseCase,
                    query
                )
            }
        ).flow.cachedIn(viewModelScope)


    companion object {
        const val NETWORK_PAGE_SIZE = 5;
    }

}