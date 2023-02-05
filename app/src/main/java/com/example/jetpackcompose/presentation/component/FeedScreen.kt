package com.example.jetpackcompose.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcompose.presentation.action.SessionAction
import com.example.jetpackcompose.presentation.event.SessionEvent
import com.example.jetpackcompose.presentation.viewmodel.SessionsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun FeedScreen(viewModel: SessionsViewModel) {
    val lazyGridState = rememberLazyGridState()
    val sessionsUiState by viewModel.sessionsUiState.collectAsStateWithLifecycle()
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        Appbar(lazyGridState, query = textState, sessionsUiState.searching) {
            textState = it
            viewModel.action(SessionAction.SearchAction(it.text))
        }

        FeedList(lazyGridState = lazyGridState, sessionList = viewModel.pagingDataFlow)
        {
            viewModel.event(SessionEvent.SearchCompletedEvent)
        }
    }
}

