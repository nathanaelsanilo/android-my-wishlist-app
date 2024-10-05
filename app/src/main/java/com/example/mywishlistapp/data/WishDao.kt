package com.example.mywishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {
    // abstract class doesnt have implementation

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wishEntity: Wish)

    @Query("select * from `wish-table`")
    // Flow -> part of kotlin coroutine to handle async data streams in a reactive way
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(entity: Wish)

    @Delete
    abstract suspend fun deleteWish(entity: Wish)

    @Query("select * from `wish-table` where id=:id")
    abstract fun getWish(id: Long): Flow<Wish>
}