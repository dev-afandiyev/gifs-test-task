/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.presentation.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(46.dp)
            )
            .then(modifier),
        onClick = { onClick() }
    ) {
        Icon(
            modifier = Modifier.size(26.dp),
            painter = painter,
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = contentDescription
        )
    }
}