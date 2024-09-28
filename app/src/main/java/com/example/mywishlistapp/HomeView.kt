package com.example.mywishlistapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Scaffold: structure of the apps (layout)
@Composable
fun HomeView() {

    Scaffold(
        topBar = { AppBarView("Wishlist") }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

        }
    }
}