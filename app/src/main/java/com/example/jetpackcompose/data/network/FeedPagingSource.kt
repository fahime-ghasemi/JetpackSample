package com.example.jetpackcompose.data.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackcompose.data.dto.toFeed
import com.example.jetpackcompose.domain.model.Feed
import com.example.jetpackcompose.repository.FeedRepository
import io.ktor.utils.io.errors.*

private const val FEED_STARTING_PAGE_INDEX = 0

class FeedPagingSource(private val feedRepository: FeedRepository) : PagingSource<Int, Feed>() {


    override fun getRefreshKey(state: PagingState<Int, Feed>): Int? {
        Log.d("fahi", "getRefreshKey " + state.anchorPosition)
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Feed> {
        val page = params.key ?: FEED_STARTING_PAGE_INDEX

        return try {
            val items = feedRepository.getFeed(page).map { it.toFeed() }
            LoadResult.Page(
                data = items,
                prevKey = if (page == FEED_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}