package com.example.jetpackcompose.presentation.component.progress

internal fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + ((stop - start) * fraction)
}

internal fun getShiftedFraction(fraction: Float, shift: Float): Float {
    val newFraction = (fraction + shift) % 1
    return (if (newFraction > .5) 1 - newFraction else newFraction) * 2
}

