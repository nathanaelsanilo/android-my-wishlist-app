package com.example.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.example.mywishlistapp.data.WishDatabase
import com.example.mywishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    // by lazy:
    // load this wishRepository once we needed and make sure there is only one instance
    // after the initialization
    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    /**
     * init the database
     */
    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context = context,
            klass = WishDatabase::class.java,
            name = "wishlist.db"
        ).build()
    }
}