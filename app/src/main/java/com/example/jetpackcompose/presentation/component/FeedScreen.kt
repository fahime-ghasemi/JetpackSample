package com.example.jetpackcompose.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.paging.PagingData
import com.example.jetpackcompose.domain.model.Feed
import com.example.jetpackcompose.presentation.viewmodel.FeedViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FeedScreen(viewModel: FeedViewModel, state: FeedState = rememberFeedState()) {
    Log.i("fahi", "recompose feedscreen")
    Column {
        Appbar(state.lazyGridState, state.query) {
            Log.i("fahi","text changed $it")
            state.query = it
        }
        LaunchedEffect(key1 = state.query.text, block = {
            if (state.query.text.isEmpty())
                state.result = viewModel.feed
            else
                state.result = viewModel.search(state.query.text)
        })
        FeedList(state.lazyGridState, state.result)
    }
}

@Composable
fun rememberFeedState(
    query: TextFieldValue = TextFieldValue(""),
    lazyGridState: LazyGridState = rememberLazyGridState(),
    result: Flow<PagingData<Feed>> = emptyFlow()
): FeedState {
    return remember(query, result) {
        FeedState(query, lazyGridState, result)
    }
}

class FeedState(
    query: TextFieldValue,
    lazyGridState: LazyGridState,
    result: Flow<PagingData<Feed>>
) {
    var query by mutableStateOf(query)
    var lazyGridState = lazyGridState
    var result by mutableStateOf(result)
}

