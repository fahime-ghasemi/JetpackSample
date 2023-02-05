package com.example.jetpackcompose.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackcompose.domain.interactor.GetSessionListUseCase
import com.example.jetpackcompose.domain.interactor.SearchSessionListUseCase
import com.example.jetpackcompose.domain.model.Session
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.toList

private const val FEED_STARTING_PAGE_INDEX = 0

class FeedPagingSource(
    private val getSessionListUseCase: GetSessionListUseCase,
    private val searchSessionListUseCase: SearchSessionListUseCase,
    private val query: String?
) :
    PagingSource<Int, Session>() {


    override fun getRefreshKey(state: PagingState<Int, Session>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Session> {
        val page = params.key ?: FEED_STARTING_PAGE_INDEX
        return try {
            val items = if (query == null) getSessionListUseCase(page).toList()
            else searchSessionListUseCase(page, query).toList()

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