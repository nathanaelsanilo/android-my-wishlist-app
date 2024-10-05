package com.example.mywishlistapp

import android.app.Application

// setup the database on this class
// important to extends the Application class
// because the ROM database is required to initialize
// inside this whole application
class WishlistApp: Application() {

    override fun onCreate() {
        super.onCreate()
        // this = the context of whole application of wishlist app
        Graph.provide(context = this)
    }
}