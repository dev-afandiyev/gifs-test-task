/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.media.gifsapp.feature.data.local.GifEntity
import com.media.gifsapp.feature.data.local.IGifsDao

@Database(
    version = 1,
    exportSchema = false,
    entities = [GifEntity::class]
)
abstract class GifsDatabase : RoomDatabase() {

    abstract fun gifsDao(): IGifsDao

}