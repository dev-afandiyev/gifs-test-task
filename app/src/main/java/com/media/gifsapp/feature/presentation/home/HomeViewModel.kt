/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.media.gifsapp.feature.domain.cases.home.HomeUseCases
import com.media.gifsapp.feature.presentation.home.state.HomeNavEffects
import com.media.gifsapp.feature.presentation.home.state.HomeNavEffects.NavigateToGif
import com.media.gifsapp.feature.presentation.home.state.HomeScreenIntent
import com.media.gifsapp.core.domain.gifs.GifExternalModel
import com.media.gifsapp.feature.utils.log.FileLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val useCases: HomeUseCases) : ViewModel() {

    private val _pagingGifs = MutableStateFlow(PagingData.empty<GifExternalModel>())
    val pagingGifs get() = _pagingGifs.asStateFlow()

    private val _sideEffects = Channel<HomeNavEffects>()
    val sideEffects = _sideEffects.receiveAsFlow()

    private var searchJob: Job? = null

    fun handleIntent(intent: HomeScreenIntent) {
        when (intent) {
            is HomeScreenIntent.Search -> searchQuery(intent.query)

            is HomeScreenIntent.RemoveGif -> viewModelScope.launch {
                useCases.markGifHidden(intent.id)
            }

            is HomeScreenIntent.OpenGif -> viewModelScope.launch {
                _sideEffects.send(NavigateToGif(intent.id))
            }
        }
    }

    private fun searchQuery(query: String) {
        if (query.isBlank()) return

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            useCases.getGifs(query)
                .cachedIn(this)
                .flowOn(Dispatchers.IO)
                .onEach { _pagingGifs.tryEmit(it) }
                .catch { FileLog.e("Invalid operation.", it) }
                .launchIn(this)
        }
    }

}