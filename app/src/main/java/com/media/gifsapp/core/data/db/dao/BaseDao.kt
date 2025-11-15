/*
* Thank you for providing the test assignment!
* It was interesting and enjoyable to work on it, and I thoroughly enjoyed the process.
*
* Copyright Shamil Afandiyev.
*/
package com.media.gifsapp.core.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<T> {

    /**
     * Insert an entity into the table.
     *
     * @param entity The entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    /**
     * Insert multiple entities into the table.
     *
     * @param entities The list of entities to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<T>)

    /**
     * Update an entity in the table.
     *
     * @param entity The entity to update.
     */
    @Update
    suspend fun update(entity: T)

    /**
     * Delete an entity from the table.
     *
     * @param entity The entity to delete.
     */
    @Delete
    suspend fun delete(entity: T)

}