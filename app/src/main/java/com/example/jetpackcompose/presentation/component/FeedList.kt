package com.example.jetpackcompose.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackcompose.domain.model.Feed
import com.example.jetpackcompose.presentation.component.progress.LineFadeProgressIndicator
import kotlinx.coroutines.flow.Flow

@Composable
fun FeedList(
    lazyGridState: LazyGridState = rememberLazyGridState(),
    feedList:Flow<PagingData<Feed>>,
    modifier: Modifier = Modifier
) {
    val res = feedList.collectAsLazyPagingItems()
    Log.i("fahi","recompose items count = ${res.itemCount}")


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
                    loadState.prepend is LoadState.Error -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LineFadeProgressIndicator(modifier = Modifier.wrapContentWidth())
                        }
                    }
                }
            }
        }
    }
}