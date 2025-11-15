/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.cases.gif

import com.media.gifsapp.feature.domain.cases.gif.mark.GifDeleteUseCase

data class GifDetailsUseCases(
    val getGifDetails: GetGifDetailsUseCase,
    val markGifHidden: GifDeleteUseCase,
)