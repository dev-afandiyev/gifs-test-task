/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.core.domain.gifs

import androidx.compose.runtime.Immutable
import com.media.gifsapp.feature.data.local.GifEntity

@Immutable
data class GifExternalModel(
    val id: String,
    val originalUrl: String? = null,
    val fixedHeightUrl: String? = null,
    val title: String? = null,
)

fun GifEntity.toGifExternalModel() = GifExternalModel(
    id = id,
    originalUrl = originalUrl,
    fixedHeightUrl = fixedHeightUrl,
    title = if (title.isNullOrBlank()) null else title
)