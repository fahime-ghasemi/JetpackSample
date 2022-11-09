package com.example.jetpackcompose.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val cardTitle = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_text_semibold, weight = FontWeight.SemiBold)),
    fontSize = 16.sp,
    color = CardItemTitle
)

val cardGenre = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_text_regular, weight = FontWeight.Normal)),
    fontSize = 12.sp,
    color = CardItemGenre
)
val cardCount = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_text_semibold, weight = FontWeight.SemiBold)),
    fontSize = 11.sp,
    color = CardItemCount
)
val searchHint = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_text_regular, weight = FontWeight.Normal)),
    fontSize = 17.sp,
    color = Hint
)

val searchBar = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_text_regular, weight = FontWeight.Normal)),
    fontSize = 24.sp,
    color = Color.White
)

val appBarBigTitle = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_display_bold, weight = FontWeight.Bold)),
    fontSize = 34.sp,
    color = Color.White
)
val appBarSmallTitle = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_display_bold, weight = FontWeight.Bold)),
    fontSize = 22.sp,
    color = Color.White
)
val countStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_text_semibold, weight = FontWeight.SemiBold)),
    fontSize = 11.sp,
    color = Color.Black
)