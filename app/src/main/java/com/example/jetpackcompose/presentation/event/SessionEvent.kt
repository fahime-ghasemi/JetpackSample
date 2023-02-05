package com.example.jetpackcompose.presentation.event

sealed interface SessionEvent {
    object SearchCompletedEvent: SessionEvent
}