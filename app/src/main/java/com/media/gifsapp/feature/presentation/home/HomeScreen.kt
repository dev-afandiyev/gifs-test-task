/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.media.gifsapp.feature.presentation.home.state.HomeNavEffects
import com.media.gifsapp.feature.presentation.home.state.HomeScreenIntent
import com.media.gifsapp.feature.presentation.custom.CustomImageView
import com.media.gifsapp.feature.presentation.custom.CustomSearch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(openGif: (String) -> Unit) {

    val viewModel = koinViewModel<HomeViewModel>()

    val gifs = viewModel.pagingGifs.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { effect ->
            if (effect is HomeNavEffects.NavigateToGif) openGif(effect.id)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // SearchBar
        CustomSearch(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            onValueChange = { viewModel.handleIntent(HomeScreenIntent.Search(it)) }
        )

        Spacer(Modifier.height(28.dp))

        // GIF List
        LazyColumn(
            modifier = Modifier.weight(1f),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(count = gifs.itemCount, key = gifs.itemKey { it.id }) { index ->
                gifs[index]?.let { gif ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                            .clickable { viewModel.handleIntent(HomeScreenIntent.OpenGif(gif.id)) },
                        contentAlignment = Alignment.Center
                    ) {
                        CustomImageView(imageUrl = gif.fixedHeightUrl)
                    }
                }
            }
        }
    }

}


