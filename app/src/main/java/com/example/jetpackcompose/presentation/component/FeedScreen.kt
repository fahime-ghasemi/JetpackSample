package com.example.jetpackcompose.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.theme.appBarBackground
import com.example.jetpackcompose.presentation.theme.appBarBigTitle
import com.example.jetpackcompose.presentation.viewmodel.FeedViewModel

@Composable
fun FeedScreen(viewModel: FeedViewModel) {
    Column{
        TopAppBar(
            backgroundColor = appBarBackground,
            modifier = Modifier.height(appBarExpandedHeight)) {
            Column(modifier = Modifier.padding(start = 16.dp, bottom = 10.dp, end = 16.dp).fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                Text(text = stringResource(id = R.string.discover) , style = appBarBigTitle)
                SearchBar()
            }
        }
        FeedList(viewModel)
    }
}

