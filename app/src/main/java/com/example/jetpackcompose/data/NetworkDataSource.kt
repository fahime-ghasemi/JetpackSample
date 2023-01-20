package com.example.jetpackcompose.data

import com.example.jetpackcompose.data.dto.toSession
import com.example.jetpackcompose.data.network.SessionService
import com.example.jetpackcompose.domain.model.Session

class NetworkDataSource(private val networkService: SessionService) : DataSource {
    override suspend fun getSessions(page: Int): List<Session> {
        return networkService.getSessions(page).map { it.toSession() }
    }

    override suspend fun search(page: Int, query: String): List<Session> {
        return networkService.search(page, query).map { it.toSession() }

    }
}