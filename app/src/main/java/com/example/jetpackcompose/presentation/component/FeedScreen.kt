package com.example.jetpackcompose.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.component.progress.lerp
import com.example.jetpackcompose.presentation.theme.appBarBackground
import com.example.jetpackcompose.presentation.theme.appBarBigTitle
import com.example.jetpackcompose.presentation.theme.appBarSmallTitle
import com.example.jetpackcompose.presentation.viewmodel.FeedViewModel

@Composable
fun FeedScreen(viewModel: FeedViewModel) {
    val lazyGridState = rememberLazyGridState()
    Column {
        Appbar(lazyGridState)
        FeedList(viewModel, lazyGridState)
    }
}

@Composable
fun Appbar(lazyGridState: LazyGridState = rememberLazyGridState()) {

    var titleHeightPX by remember { mutableStateOf(0f) }
    var titleWidthPX by remember { mutableStateOf(0f) }
    val titleHeightDP = with(LocalDensity.current) {
        titleHeightPX.toDp()
    }
    val titleWidthDP = with(LocalDensity.current) {
        titleWidthPX.toDp()
    }
    var appbarWidthPX by remember {
        mutableStateOf(0f)
    }
    val appbarWidthDP = with(LocalDensity.current)
    {
        appbarWidthPX.toDp()
    }

    val changeRange = appBarExpandedHeight - appBarCollapsedHeight

    /**
     * I have to add this if statement because in grid firstVisibleItemScrollOffset resets to zero for each line
     */
    val fraction = if (lazyGridState.firstVisibleItemIndex != 0) 1f
    else
        with(LocalDensity.current)
        {
            (lazyGridState.firstVisibleItemScrollOffset / changeRange.toPx()).coerceIn(
                0f,
                1f
            )
        }

    val toolbarHeight = lerp(appBarExpandedHeight, appBarCollapsedHeight, fraction)
    val titleY = lerp(
        start = appBarExpandedHeight - titleHeightDP - 10.dp - searchBarHeight,
        stop = appBarCollapsedHeight - 10.dp - titleHeightDP,
        fraction = fraction
    )
    val titleX = lerp(start = 16.dp, stop = (appbarWidthDP - titleWidthDP) / 2, fraction = fraction)
    val searchBarY = lerp(appBarExpandedHeight - searchBarHeight - 10.dp, 0.dp, fraction)
    val style = lerp(appBarBigTitle, appBarSmallTitle, fraction)
    val searchBarAlpha = lerp(1f, 0f, (fraction * 2).coerceIn(0f, 1f))

    TopAppBar(
        backgroundColor = appBarBackground,
        modifier = Modifier
            .height(toolbarHeight)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 10.dp, end = 16.dp)
                .fillMaxSize()
                .onGloballyPositioned {
                    appbarWidthPX = it.size.width.toFloat()
                }
        ) {
            Text(
                modifier = Modifier
                    .graphicsLayer {
                        translationY = titleY.toPx()
                        translationX = titleX.toPx()
                    }
                    .onGloballyPositioned {
                        titleHeightPX = it.size.height.toFloat()
                        titleWidthPX = it.size.width.toFloat()
                    },
                text = stringResource(id = R.string.discover), style = style
            )
            SearchBar(
                Modifier
                    .height(searchBarHeight)
                    .graphicsLayer {
                        translationY = searchBarY.toPx()
                        alpha = searchBarAlpha
                    })
        }
    }
}

@Preview
@Composable
fun AppbarPreview() {
    Appbar()
}
