/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.media.gifsapp.core.navigation.NavigationRoot
import com.media.gifsapp.core.ui.theme.GifsAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GifsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
                    NavigationRoot(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPaddings)
                            .windowInsetsPadding(WindowInsets.displayCutout)
                    )
                }
            }
        }
    }

}