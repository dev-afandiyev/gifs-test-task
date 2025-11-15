/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.media.gifsapp.feature.data.local.GifEntity
import com.media.gifsapp.feature.data.local.IGifsDao
import com.media.gifsapp.feature.data.remote.api.IGifsApi
import com.media.gifsapp.feature.data.remote.api.toGifEntity

@OptIn(ExperimentalPagingApi::class)
class GifsRemoteMediator(
    private val gifsDao: IGifsDao,
    private val gifsApi: IGifsApi,
    private val query: String
) : RemoteMediator<Int, GifEntity>() {

    private var pageIndex = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifEntity>
    ): MediatorResult {

        pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize
        val offset = pageIndex * limit

        return try {
            val launches = fetchResponse(offset = offset, limit = limit)
            val ids = launches.map { it.id }

            if (loadType == LoadType.REFRESH) {
                gifsDao.refresh(ids, launches)
            } else {
                gifsDao.insert(launches)
            }

            MediatorResult.Success(
                endOfPaginationReached = launches.size < limit
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    private suspend fun fetchResponse(offset: Int, limit: Int): List<GifEntity> {
        val response = gifsApi.getGifs(
            offset = offset,
            limit = limit,
            query = query,
            lang = "en",
            rating = "g",
            bundle = "messaging_non_clips",
        )

        return response.body()?.data
            .orEmpty()
            .mapNotNull { it.toGifEntity() }
            .map {
                it.copy(
                    lastQuery = query
                )
            }
    }

}