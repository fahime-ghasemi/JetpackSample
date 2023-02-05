package com.example.jetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpackcompose.data.network.FeedPagingSource
import com.example.jetpackcompose.domain.interactor.GetSessionListUseCase
import com.example.jetpackcompose.domain.interactor.SearchSessionListUseCase
import com.example.jetpackcompose.domain.model.Session
import com.example.jetpackcompose.presentation.action.SessionAction
import com.example.jetpackcompose.presentation.event.SessionEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SessionsViewModel(
    private val getSessionListUseCase: GetSessionListUseCase,
    private val searchSessionListUseCase: SearchSessionListUseCase
) : ViewModel() {

    var pagingDataFlow: Flow<PagingData<Session>>
        private set

    private val _sessionsUiState = MutableStateFlow(SessionsUiState(false))
    val sessionsUiState = _sessionsUiState.asStateFlow()

    val action: (SessionAction) -> Unit
    val event: (SessionEvent) -> Unit


    init {
        pagingDataFlow = feed()
        val actionSharedFlow = MutableSharedFlow<SessionAction>()
        val eventSharedFlow = MutableSharedFlow<SessionEvent>()

        val search = actionSharedFlow.filterIsInstance<SessionAction.SearchAction>()
            .distinctUntilChanged ()

        viewModelScope.launch {

            search.collectLatest {
                pagingDataFlow = if (it.query.isEmpty())
                    feed()
                else
                    search(query = it.query)

                _sessionsUiState.update { state -> state.copy(searching = true) }
            }
        }

        eventSharedFlow.filterIsInstance<SessionEvent.SearchCompletedEvent>()
            .distinctUntilChanged()

        viewModelScope.launch {
            eventSharedFlow.collect {
                _sessionsUiState.update { it.copy(searching = false) }
            }
        }

        action = { action ->
            viewModelScope.launch {
                actionSharedFlow.emit(action)
            }
        }

        event = { event ->
            viewModelScope.launch {
                eventSharedFlow.emit(event)
            }
        }
    }

    private fun feed() = Pager(
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
    ).flow


    private fun search(query: String): Flow<PagingData<Session>> {
        return Pager(
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
        ).flow

    }


    companion object {
        const val NETWORK_PAGE_SIZE = 5;
    }

    data class SessionsUiState(val searching: Boolean)
}