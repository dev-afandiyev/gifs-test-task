/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp

import android.app.Application
import com.media.gifsapp.di.modules.db.databaseModule
import com.media.gifsapp.di.modules.network.networkModule
import com.media.gifsapp.feature.domain.di.gifModule
import com.media.gifsapp.feature.domain.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GifsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@GifsApplication)

            modules(
                networkModule,
                databaseModule,
                gifModule,
                homeModule,
            )
        }
    }

}