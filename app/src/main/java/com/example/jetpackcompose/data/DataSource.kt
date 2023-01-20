package com.example.jetpackcompose.data

import com.example.jetpackcompose.domain.model.Session

interface DataSource {
    suspend fun getSessions(page: Int): List<Session>
    suspend fun search(page: Int, query: String): List<Session>
}