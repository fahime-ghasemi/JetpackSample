package com.example.jetpackcompose.data.network

import com.example.jetpackcompose.data.dto.SessionDto

interface SessionService {
    suspend fun getSessions(page: Int): List<SessionDto>
    suspend fun search(page: Int, query:String): List<SessionDto>
}