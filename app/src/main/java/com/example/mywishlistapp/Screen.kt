package com.example.mywishlistapp

// sealed class
// limit class inheritance
// A sealed class itself is always an abstract class
sealed class Screen(val route: String) {
    object HomeScreen: Screen(route = "home-screen")
    object AddScreen: Screen(route = "add-screen")
}