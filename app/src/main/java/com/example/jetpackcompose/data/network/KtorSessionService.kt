package com.example.jetpackcompose.data.network

import com.example.jetpackcompose.data.dto.SessionDto
import com.example.jetpackcompose.data.network.response.FeedResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class KtorSessionService(private val client: HttpClient) : SessionService {

    override suspend fun getSessions(page: Int): List<SessionDto> {
        return try {
            if (page < 5)
                client.get<FeedResponse> {
                    url(ApiRoutes.FEED)
                }.data.sessions
            else
                emptyList()
        } catch (e: ServerResponseException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * I imagined that always we have one page of search result
     */
    override suspend fun search(page: Int, query: String): List<SessionDto> {
        return try {
            if (page == 0)
                client.get<FeedResponse> {
                    url(ApiRoutes.SEARCH)
                }.data.sessions
            else
                emptyList()

        } catch (e: ServerResponseException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}