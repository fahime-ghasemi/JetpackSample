package com.example.jetpackcompose.domain.model.repository

import com.example.jetpackcompose.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun getSessions(page: Int): Flow<Session>
    suspend fun search(page: Int, query: String): Flow<Session>
}