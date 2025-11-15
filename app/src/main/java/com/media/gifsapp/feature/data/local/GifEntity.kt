/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gif")
data class GifEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "type")
    val type: String? = null,
    @ColumnInfo(name = "original_url")
    val originalUrl: String? = null,
    @ColumnInfo(name = "fixed_height_url")
    val fixedHeightUrl: String? = null,
    @ColumnInfo(name = "user_name")
    val userName: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "import_date_time")
    val importDateTime: String? = null,
    @ColumnInfo(name = "last_query")
    val lastQuery: String? = null,
    @ColumnInfo(name = "alt_text")
    val altText: String? = null,
    @ColumnInfo(name = "low_contrast")
    val isLowContrast: Boolean? = null,
    @ColumnInfo(name = "hidden")
    val isHidden: Boolean = false
)