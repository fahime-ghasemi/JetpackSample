package com.example.jetpackcompose.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpackcompose.data.network.FeedPagingSource
import com.example.jetpackcompose.data.network.FeedService
import com.example.jetpackcompose.domain.model.Feed
import kotlinx.coroutines.flow.Flow

/**
 * According to Google sample, Paging should be done in Repository layer. I mean we should
 * use Payer here not in viewmodel layer
 */
class FeedRepositoryImpl(private val feedService: FeedService) : FeedRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    override fun getFeed(): Flow<PagingData<Feed>> {
        Log.i("fahi","repository getFeed")

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FeedPagingSource(feedService,null) }
        ).flow
    }

    override fun search(query: String): Flow<PagingData<Feed>> {
        Log.i("fahi","repository search $query")
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FeedPagingSource(feedService,query) }
        ).flow
    }
}