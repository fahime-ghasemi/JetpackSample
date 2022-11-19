package com.example.jetpackcompose.data.network

import android.util.Log
import com.example.jetpackcompose.data.dto.FeedDto
import com.example.jetpackcompose.data.network.response.FeedResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class FeedServiceImpl(private val client: HttpClient) : FeedService {

    override suspend fun getFeed(page: Int): List<FeedDto> {
        Log.i("fahi","getFeed is $page")

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
            Log.d("fahi", e.printStackTrace().toString())
            emptyList()
        }
    }

    /**
     * I imagined that always we have one page of search result
     */
    override suspend fun searchFeed(page: Int, query: String): List<FeedDto> {
        return try {
            Log.i("fahi","searchFeed is $page")
            if (page == 0)
                client.get<FeedResponse> {
                    url(ApiRoutes.SEARCH)
                }.data.sessions
            else
                emptyList()

        } catch (e: ServerResponseException) {
            emptyList()
        } catch (e: Exception) {
            Log.d("fahi", e.printStackTrace().toString())
            emptyList()
        }
    }
}