package com.example.jetpackcompose.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.paging.PagingData
import com.example.jetpackcompose.domain.model.Feed
import com.example.jetpackcompose.presentation.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FeedScreen(viewModel: FeedViewModel, state: FeedState = rememberFeedState()) {
    Log.i("fahi", "FeedScreen")
    Column {
        Appbar(state.lazyGridState, state.query, state.searching) {
            state.query = it
        }

        LaunchedEffect(key1 = state.query.text, block = {
            if (state.query.text.isEmpty()) {
                state.result = viewModel.feed
            } else {
                state.searching = true
                state.result = viewModel.search(state.query.text)
            }
        })
        FeedList(state.lazyGridState, state.result)
        {
            Log.i("fahi", "onDataLoaded ")
            state.searching = false
        }
    }
}

@Composable
fun rememberFeedState(
    query: TextFieldValue = TextFieldValue(""),
    lazyGridState: LazyGridState = rememberLazyGridState(),
    result: Flow<PagingData<Feed>> = emptyFlow(),
    searching: Boolean = false
): FeedState {
    return remember {
        FeedState(query, lazyGridState, result, searching)
    }
}

class FeedState(
    query: TextFieldValue,
    lazyGridState: LazyGridState,
    result: Flow<PagingData<Feed>>,
    searching: Boolean
) {
    var query by mutableStateOf(query)
    var lazyGridState = lazyGridState
    var result by mutableStateOf(result)
    var searching by mutableStateOf(searching)
}

