/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.data.repository.gif

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.media.gifsapp.feature.data.local.GifEntity
import com.media.gifsapp.feature.data.local.IGifsDao
import com.media.gifsapp.feature.data.remote.api.IGifsApi
import com.media.gifsapp.feature.data.remote.mediator.GifsRemoteMediator
import com.media.gifsapp.feature.domain.repository.IGifsRepository

class GifsRepositoryImpl(
    private val gifsDao: IGifsDao,
    private val gifsApi: IGifsApi
) : IGifsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getGifsList(query: String): Pager<Int, GifEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                prefetchDistance = PAGE_SIZE / 2
            ),
            remoteMediator = GifsRemoteMediator(
                gifsDao = gifsDao,
                gifsApi = gifsApi,
                query = query
            ),
            pagingSourceFactory = { gifsDao.getPaging(query) }
        )
    }

    private companion object {
        const val PAGE_SIZE = 15
    }

}