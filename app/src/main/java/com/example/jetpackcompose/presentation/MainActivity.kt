package com.example.jetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.jetpackcompose.presentation.component.FeedScreen
import com.example.jetpackcompose.presentation.theme.JetpackComposeTheme
import com.example.jetpackcompose.presentation.theme.mainBackground
import com.example.jetpackcompose.presentation.viewmodel.FeedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<FeedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = mainBackground
                ) {
                        FeedScreen(viewModel)
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    JetpackComposeTheme {
//        FeedScreen(viewModel = )
//    }
//}