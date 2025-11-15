/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.gif

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.media.gifsapp.core.domain.gifs.GifExternalModel
import com.media.gifsapp.feature.domain.cases.gif.GifDetailsUseCases
import com.media.gifsapp.feature.presentation.gif.state.GifNavEffects
import com.media.gifsapp.feature.presentation.gif.state.GifScreenIntent
import com.media.gifsapp.feature.presentation.gif.state.GifScreenUiState
import com.media.gifsapp.feature.utils.log.FileLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GifViewModel(
    private val appContext: Context,
    private val useCases: GifDetailsUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(GifScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    private val _sideEffects = Channel<GifNavEffects>()
    val sideEffects = _sideEffects.receiveAsFlow()

    fun handleIntent(intent: GifScreenIntent) {
        when (intent) {
            is GifScreenIntent.GoBack -> initBackNavigation()

            is GifScreenIntent.GetGifDetails -> getGifDetails(intent.id)

            is GifScreenIntent.GifDelete -> {
                markGifAsHidden(intent.id)
                initBackNavigation()
            }
        }
    }

    private fun initBackNavigation() {
        viewModelScope.launch {
            _sideEffects.send(GifNavEffects.BackNavigation)
        }
    }

    private fun markGifAsHidden(id: String?) {
        id ?: return

        viewModelScope.launch(Dispatchers.IO) {
            useCases.markGifHidden(id)
        }
    }

    private fun getGifDetails(id: String) {
        useCases.getGifDetails(id)
            .flowOn(Dispatchers.IO)
            .onEach { item ->
                val url = selectImageUrl(item)
                _uiState.update { it.copy(item = item, imageUrl = url) }
            }
            .catch { FileLog.e("Invalid operation.", it) }
            .launchIn(viewModelScope)
    }

    private fun selectImageUrl(item: GifExternalModel): String? {
        return if (isInternetAvailable()) item.originalUrl else item.fixedHeightUrl
    }

    //MARK: I can't shake the feeling that this is unnecessary here, but I haven't come up with anything better yet.
    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

}