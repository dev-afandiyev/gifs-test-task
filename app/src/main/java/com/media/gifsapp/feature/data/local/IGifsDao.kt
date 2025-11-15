/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.feature.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.media.gifsapp.core.data.db.dao.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface IGifsDao : BaseDao<GifEntity> {

    /**
     * Get gifs paging source
     */
    @Query("SELECT * FROM gif WHERE (last_query LIKE '%' || :query || '%' OR title LIKE '%' || :query || '%')")
    fun getPaging(query: String): PagingSource<Int, GifEntity>

    /**
     * Delete old records
     */
    @Query("DELETE FROM gif WHERE id IN (:itemsIds)")
    fun delete(itemsIds: List<String>?)

    /**
     * Get item by id
     */
    @Query("SELECT * FROM gif WHERE id = :id")
    suspend fun getItem(id: String): GifEntity?

    /**
     * Set isHidden value
     */
    @Query("UPDATE gif SET hidden = :isHidden WHERE id = :id")
    suspend fun setIsHidden(id: String, isHidden: Boolean)

    /**
     * Get item flow by id
     */
    @Query("SELECT * FROM gif WHERE id = :id")
    fun getItemFlow(id: String): Flow<GifEntity?>

    /**
     * Clear old records and place new records from the list.
     */
    @Transaction
    suspend fun refresh(itemsIds: List<String>?, items: List<GifEntity>) {
        delete(itemsIds)
        insert(items)
    }

}