/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.gif.state

import com.media.gifsapp.core.domain.gifs.GifExternalModel

data class GifScreenUiState(
    val item: GifExternalModel? = null,
    val imageUrl: String? = null
)
