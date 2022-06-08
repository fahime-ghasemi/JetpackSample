package com.example.jetpackcompose.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpackcompose.presentation.component.progress.LineFadeProgressIndicator
import com.example.jetpackcompose.presentation.viewmodel.FeedViewModel

@Composable
fun FeedList(
    viewModel: FeedViewModel,
    modifier: Modifier = Modifier
) {
    val res = viewModel.getFeedResultStream().collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
//            state = scrollState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(res.itemCount)
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