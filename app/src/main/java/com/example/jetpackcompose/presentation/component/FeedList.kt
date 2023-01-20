package com.example.jetpackcompose.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackcompose.domain.model.Session
import com.example.jetpackcompose.presentation.component.progress.LineFadeProgressIndicator
import kotlinx.coroutines.flow.Flow

@Composable
fun FeedList(
    lazyGridState: LazyGridState = rememberLazyGridState(),
    sessionList: Flow<PagingData<Session>>,
    modifier: Modifier = Modifier,
    onDataLoaded: () -> Unit
) {
    val res = sessionList.collectAsLazyPagingItems()
    if (res.itemCount > 0) {
        onDataLoaded()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(res.itemCount, key = { index ->
                index
            })
            { index ->
                res[index]?.let {
                    FeedItem(it)
                }
            }

            res.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LineFadeProgressIndicator(modifier = Modifier.wrapContentWidth())
                        }

                    }
                    loadState.append is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LineFadeProgressIndicator(modifier = Modifier.wrapContentWidth())
                        }
                    }
                    loadState.prepend is LoadState.Loading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LineFadeProgressIndicator(modifier = Modifier.wrapContentWidth())
                        }
                    }
                }
            }
        }
    }
}