package com.example.jetpackcompose.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
//                    testView()
                }
            }
        }
    }
}

@Composable
fun testView() {
    var text by remember { mutableStateOf("") }

    BasicTextField(modifier = Modifier.fillMaxWidth().height(34.dp), value = text, onValueChange = {
        Log.i("fahi","onValueChange")
        text = it })
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    JetpackComposeTheme {
//        FeedScreen(viewModel = )
//    }
//}