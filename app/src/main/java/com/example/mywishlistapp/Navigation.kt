package com.example.mywishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val createFlag: Long = -1
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeView(navController, viewModel)
        }
        composable(Screen.AddScreen.route + "/{id}", arguments = listOf(
            navArgument(
                name = "id"
            ) {
                type = NavType.LongType
                defaultValue = createFlag
                nullable = false
            }
        )) { entry ->
            val wishId = if (entry.arguments != null) entry.arguments!!.getLong("id") else createFlag
            FormView(id = wishId, viewModel = viewModel, navController = navController)
        }
    }
}