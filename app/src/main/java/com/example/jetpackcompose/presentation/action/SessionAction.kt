package com.example.jetpackcompose.presentation.action

sealed interface SessionAction {
    data class SearchAction(val query: String) : SessionAction
}