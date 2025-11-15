/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.home.state

sealed class HomeScreenIntent {

    data class OpenGif(val id: String) : HomeScreenIntent()

    data class RemoveGif(val id: String) : HomeScreenIntent()

    data class Search(val query: String) : HomeScreenIntent()

}