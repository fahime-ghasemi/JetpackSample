package com.example.jetpackcompose

import com.example.jetpackcompose.domain.model.Session
import com.example.jetpackcompose.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class TestSessionRepository : SessionRepository {
    private val sessions = mutableListOf<Session>()

    override suspend fun getSessions(page: Int): Flow<Session> {
        return sessions.asFlow()
    }

    override suspend fun search(page: Int, query: String): Flow<Session> {
        return sessions.filter { it.name.contains(query) }.asFlow()
    }

    fun addSession(session: Session) {
        sessions.add(session)
    }
}