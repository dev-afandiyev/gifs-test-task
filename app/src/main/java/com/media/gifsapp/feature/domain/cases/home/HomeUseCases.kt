/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.domain.cases.home

import com.media.gifsapp.feature.domain.cases.gif.mark.GifDeleteUseCase
import com.media.gifsapp.feature.domain.cases.home.list.GetGifsUseCase

data class HomeUseCases(
    val markGifHidden: GifDeleteUseCase,
    val getGifs: GetGifsUseCase,
)