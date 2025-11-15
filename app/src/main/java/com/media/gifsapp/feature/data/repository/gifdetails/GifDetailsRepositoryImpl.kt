/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.data.repository.gifdetails

import com.media.gifsapp.feature.domain.repository.IGifDetailsRepository
import com.media.gifsapp.feature.data.local.GifEntity
import com.media.gifsapp.feature.data.local.IGifsDao
import kotlinx.coroutines.flow.Flow

class GifDetailsRepositoryImpl(private val gifsDao: IGifsDao) : IGifDetailsRepository {

    override fun getGif(id: String): Flow<GifEntity?> {
        return gifsDao.getItemFlow(id)
    }

    override suspend fun setGifIsHidden(id: String): GifEntity? {
        gifsDao.setIsHidden(id = id, isHidden = true)

        return gifsDao.getItem(id)
    }

}