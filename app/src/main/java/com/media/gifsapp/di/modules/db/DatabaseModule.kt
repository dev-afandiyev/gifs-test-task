/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.di.modules.db

import androidx.room.Room
import com.media.gifsapp.core.data.db.GifsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<GifsDatabase> {
        val builder = Room.databaseBuilder(
            context = androidContext(),
            klass = GifsDatabase::class.java,
            name = "GifsDatabase"
        ).fallbackToDestructiveMigration(false)

        builder.build()
    }

}