/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.cases.gif

import com.media.gifsapp.feature.domain.repository.IGifDetailsRepository
import com.media.gifsapp.core.domain.gifs.GifExternalModel
import com.media.gifsapp.core.domain.gifs.toGifExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class GetGifDetailsUseCase(private val repository: IGifDetailsRepository) {

    operator fun invoke(id: String): Flow<GifExternalModel> {
        return repository.getGif(id).mapNotNull {
            it?.toGifExternalModel()
        }
    }

}