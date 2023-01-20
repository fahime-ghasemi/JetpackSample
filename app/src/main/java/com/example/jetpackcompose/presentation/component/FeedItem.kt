package com.example.jetpackcompose.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.data.dto.TrackDto
import com.example.jetpackcompose.domain.model.Session
import com.example.jetpackcompose.presentation.theme.JetpackComposeTheme
import com.example.jetpackcompose.presentation.theme.cardGenre
import com.example.jetpackcompose.presentation.theme.cardTitle
import com.example.jetpackcompose.presentation.theme.countBg
import com.example.jetpackcompose.presentation.theme.countStyle
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FeedItem(item: Session?) {
    Card(shape = RoundedCornerShape(10.dp)) {
        Box(modifier = Modifier.aspectRatio(1f)) {
            GlideImage(
                imageModel = item!!.currentTrack.artwork,
                placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
                error = ImageBitmap.imageResource(R.drawable.placeholder),
                contentScale = ContentScale.FillBounds
            )
            CountItem(modifier = Modifier.padding(top = 8.dp, start = 9.dp), count = item.listenerCount)
            Column(
                modifier = Modifier
                    .padding(start = 9.dp, bottom = 9.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    style = cardTitle,
                    text = item.name
                )
                Text(
                    style = cardGenre,
                    text = item.genres.joinToString()
                )
            }


        }
    }
}

@Composable
fun CountItem(modifier: Modifier = Modifier,count: Int) {
    Box(modifier = modifier) {
        Row(
            Modifier
                .background(countBg, RoundedCornerShape(9.dp))
                .padding(start = 6.dp, end = 6.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_headphone),
                contentDescription = null
            )
            Text(modifier = Modifier.padding(start = 2.dp), text = "$count", style = countStyle)
        }
    }
}

@Preview
@Composable
fun previewCountItem() {
    CountItem(count = 2)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        FeedItem(
            item = Session(
                "hi",
                2,
                arrayListOf(),
                TrackDto("hi", "https://i.scdn.co/image/05c1c3fa2e2cca7011c8c94751d7f21f4aff5b54")
            )
        )
    }
}