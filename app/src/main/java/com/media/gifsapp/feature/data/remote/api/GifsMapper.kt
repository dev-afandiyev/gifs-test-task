/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.data.remote.api

import com.media.gifsapp.feature.data.local.GifEntity

fun Data.toGifEntity(): GifEntity? {
    id ?: return null

    return GifEntity(
        id = id,
        type = type,
        originalUrl = images?.original?.url,
        fixedHeightUrl = images?.fixedHeightSmall?.url,
        userName = username,
        title = title,
        importDateTime = importDatetime,
        altText = altText,
        isLowContrast = isLowContrast
    )
}