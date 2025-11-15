/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.home.state

sealed class HomeNavEffects {

    data class NavigateToGif(val id: String) : HomeNavEffects()

}