/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.repository

import androidx.paging.Pager
import com.media.gifsapp.feature.data.local.GifEntity

interface IGifsRepository {

    suspend fun getGifsList(query: String): Pager<Int, GifEntity>

}