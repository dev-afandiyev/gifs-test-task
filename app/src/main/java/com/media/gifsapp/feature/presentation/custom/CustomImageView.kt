/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.custom

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.decode.ImageDecoderDecoder
import com.media.gifsapp.R
import kotlinx.coroutines.Dispatchers

@Composable
fun CustomImageView(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    widthDp: Dp = 350.dp,
    heightDp: Dp = 250.dp,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current
    val dispatcher = Dispatchers.IO.limitedParallelism(5)

    Box(
        modifier = modifier
            .size(width = widthDp, height = heightDp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentDescription = null,
            contentScale = contentScale,
            alignment = Alignment.Center,
            error = {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        tint = MaterialTheme.colorScheme.primary,
                        painter = painterResource(R.drawable.ic_broken_gif),
                        contentDescription = stringResource(R.string.placeholder)
                    )
                }
            },
            loading = {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp))
                }
            },
            imageLoader = ImageLoader.Builder(context)
                .dispatcher(dispatcher)
                .components { add(ImageDecoderDecoder.Factory()) }
                .respectCacheHeaders(false)
                .build()
        )
    }
}
