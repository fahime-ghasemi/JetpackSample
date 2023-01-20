package com.example.jetpackcompose.domain.model.repository

import com.example.jetpackcompose.data.DataSource
import com.example.jetpackcompose.domain.model.Session
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class SessionRepositoryImpl(private val dataSource: DataSource) : SessionRepository {

    override suspend fun getSessions(page: Int): Flow<Session> {
        return dataSource.getSessions(page).asFlow()
    }

    override suspend fun search(page: Int, query: String): Flow<Session> {
        return dataSource.getSessions(page).asFlow()
    }
}