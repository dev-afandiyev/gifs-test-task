/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.repository

import com.media.gifsapp.feature.data.local.GifEntity
import kotlinx.coroutines.flow.Flow

interface IGifDetailsRepository {

    fun getGif(id: String): Flow<GifEntity?>

    suspend fun setGifIsHidden(id: String): GifEntity?

}