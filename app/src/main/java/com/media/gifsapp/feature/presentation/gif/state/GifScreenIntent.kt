/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.gif.state

sealed class GifScreenIntent {

    data class GetGifDetails(val id: String): GifScreenIntent()

    data class GifDelete(val id: String?): GifScreenIntent()

    data object GoBack: GifScreenIntent()

}