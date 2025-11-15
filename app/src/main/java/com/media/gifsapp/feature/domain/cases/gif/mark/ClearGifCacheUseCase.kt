/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.cases.gif.mark

import android.content.Context
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import coil.memory.MemoryCache
import com.media.gifsapp.feature.data.local.GifEntity
import com.media.gifsapp.feature.utils.log.FileLog

class ClearGifCacheUseCase(private val context: Context) {

    operator fun invoke(item: GifEntity) {
        listOf(item.originalUrl, item.fixedHeightUrl).forEach {
            clearCoilCacheUrl(it)
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    private fun clearCoilCacheUrl(url: String?) {
        url ?: return

        try {
            context.imageLoader.memoryCache?.remove(MemoryCache.Key(url))
            context.imageLoader.diskCache?.remove(url)
        } catch (e: Exception) {
            FileLog.e("Coil clear url - $url cache invalid operation.", e)
        }
    }

}