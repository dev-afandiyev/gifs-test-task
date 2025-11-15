/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.gif

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.media.gifsapp.R
import com.media.gifsapp.feature.presentation.custom.CustomBackButton
import com.media.gifsapp.feature.presentation.custom.CustomIconButton
import com.media.gifsapp.feature.presentation.custom.CustomImageView
import com.media.gifsapp.feature.presentation.custom.CustomText
import com.media.gifsapp.feature.presentation.gif.state.GifNavEffects
import com.media.gifsapp.feature.presentation.gif.state.GifScreenIntent
import com.media.gifsapp.feature.presentation.gif.state.GifScreenUiState
import com.media.gifsapp.feature.utils.downloader.GifDownloader
import org.koin.androidx.compose.koinViewModel

@Composable
fun GifScreen(id: String, navigateBack: () -> Unit) {

    val configuration = LocalConfiguration.current

    val viewModel = koinViewModel<GifViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(GifScreenIntent.GetGifDetails(id))
        viewModel.sideEffects.collect {
            if (it is GifNavEffects.BackNavigation) navigateBack()
        }
    }

    val imageUrl = uiState.imageUrl

    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        GifScreenContent(
            uiState = uiState,
            imageUrl = imageUrl,
            isPortrait = true,
            onIntent = viewModel::handleIntent
        )
    } else {
        GifScreenContent(
            uiState = uiState,
            imageUrl = imageUrl,
            isPortrait = false,
            onIntent = viewModel::handleIntent
        )
    }
}

@Composable
fun GifScreenContent(
    uiState: GifScreenUiState,
    imageUrl: String?,
    isPortrait: Boolean,
    onIntent: (GifScreenIntent) -> Unit
) {
    val arrangement = if (isPortrait) Arrangement.spacedBy(16.dp) else Arrangement.spacedBy(12.dp)
    val modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)

    if (isPortrait) {
        Column(modifier = modifier, verticalArrangement = arrangement) {
            CustomBackButton { onIntent(GifScreenIntent.GoBack) }
            TitleAndActions(uiState, onIntent)
            CustomImageView(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), imageUrl = imageUrl)
        }
    } else {
        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            CustomBackButton { onIntent(GifScreenIntent.GoBack) }
            CustomImageView(modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(), imageUrl = imageUrl)
            Column(verticalArrangement = arrangement) {
                TitleAndActions(uiState, onIntent)
            }
        }
    }
}

@Composable
fun TitleAndActions(uiState: GifScreenUiState, onIntent: (GifScreenIntent) -> Unit) {
    val context = LocalContext.current

    CustomText(
        text = uiState.item?.title ?: stringResource(R.string.unknown),
        fontSize = 18.sp
    )

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        CustomIconButton(
            painter = painterResource(R.drawable.ic_delete),
            contentDescription = stringResource(R.string.delete)
        ) { onIntent(GifScreenIntent.GifDelete(uiState.item?.id)) }

        CustomIconButton(
            painter = painterResource(R.drawable.ic_share),
            contentDescription = stringResource(R.string.share)
        ) {
            val gifUrl = uiState.imageUrl ?: return@CustomIconButton

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, gifUrl)
            }

            context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.unknown)))
        }

        CustomIconButton(
            painter = painterResource(R.drawable.ic_download),
            contentDescription = stringResource(R.string.download)
        ) {
            //MARK: Not the best solution, but I'm tired, boss.
            val gifUrl =  uiState.imageUrl ?: return@CustomIconButton
            GifDownloader.downloadGif(context, gifUrl)
        }
    }
}
