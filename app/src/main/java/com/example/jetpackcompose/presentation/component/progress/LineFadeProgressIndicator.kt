package com.example.jetpackcompose.presentation.component.progress

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.presentation.theme.JetpackComposeTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private const val DefaultAnimationDuration = 1000

private val DefaultRectWidth = 2.dp
private val DefaultRectHeight = 5.dp
private val DefaultRectCornerRadius = 2.dp
private val DefaultDiameter = 20.dp

private const val DefaultMaxAlpha = 1f
private const val DefaultMinAlpha = .1f

@Composable
fun LineFadeProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF8E8E93),
    animationDuration: Int = DefaultAnimationDuration,
    rectWidth: Dp = DefaultRectWidth,
    rectHeight: Dp = DefaultRectHeight,
    rectCornerRadius: Dp = DefaultRectCornerRadius,
    diameter: Dp = DefaultDiameter,
    maxAlpha: Float = DefaultMaxAlpha,
    minAlpha: Float = DefaultMinAlpha,
    isClockwise: Boolean = true
) {
    val transition = rememberInfiniteTransition()

    val fraction by transition.fraction(animationDuration)

    ProgressIndicator(modifier, diameter) {
        val alphaList = mutableListOf<Float>()
        for (i in 0..11) {
            val newFraction =
                getShiftedFraction(if (isClockwise) 1 - fraction else fraction, .1f * i)
            lerp(minAlpha, maxAlpha, newFraction).also { alphaList.add(it) }
        }

        drawIndeterminateLineSpinFadeLoaderIndicator(
            rectWidth = rectWidth.toPx(),
            rectHeight = rectHeight.toPx(),
            rectCornerRadius = rectCornerRadius.toPx(),
            alpha = alphaList,
            color = color
        )
    }
}

private fun DrawScope.drawIndeterminateLineSpinFadeLoaderIndicator(
    rectWidth: Float,
    rectHeight: Float,
    rectCornerRadius: Float,
    alpha: List<Float>,
    color: Color
) {

    for (i in 0..11) {
        val angle = PI.toFloat() / 6 * i
        val x = size.width * cos(angle) / 2
        val y = size.height * sin(angle) / 2

        rotate(
            degrees = (30f * i)+90f ,
            pivot = center + Offset(x, y)
        ) {
            drawRoundRect(
                color = color,
                size = Size(rectWidth, rectHeight),
                topLeft = center + Offset(-rectWidth / 2, 0f) + Offset(x, y),
                cornerRadius = CornerRadius(rectCornerRadius),
                alpha = alpha[i]
            )
        }
    }
}

@Composable
internal fun ProgressIndicator(
    modifier: Modifier,
    size: Dp,
    onDraw: DrawScope.() -> Unit
) {
    Canvas(
        modifier = modifier
            .progressSemantics()
            .size(size)
            .focusable(),
        onDraw = onDraw
    )
}

@Preview(showBackground = true)
@Composable
fun ProgressPreview() {
    JetpackComposeTheme {
        LineFadeProgressIndicator()
    }
}
