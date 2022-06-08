package com.example.jetpackcompose.presentation.component.progress

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
internal fun InfiniteTransition.fraction(
    durationMillis: Int,
    delayMillis: Int = 0,
    easing: Easing = LinearEasing
): State<Float> {
    val duration = durationMillis + delayMillis

    return animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                this.durationMillis = duration
                0f at delayMillis / 2 with easing
                1f at durationMillis + (delayMillis / 2)
                1f at duration
            }
        )
    )
}
