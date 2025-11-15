/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.cases.gif.mark

import com.media.gifsapp.core.domain.gifs.GifExternalModel
import com.media.gifsapp.core.domain.gifs.toGifExternalModel
import com.media.gifsapp.feature.domain.repository.IGifDetailsRepository

class GifDeleteUseCase(
    private val repository: IGifDetailsRepository,
    private val clearCache: ClearGifCacheUseCase
) {

    suspend operator fun invoke(id: String): GifExternalModel? {
        return repository.setGifIsHidden(id)?.let {
            clearCache(it)

            it.toGifExternalModel()
        }
    }

}