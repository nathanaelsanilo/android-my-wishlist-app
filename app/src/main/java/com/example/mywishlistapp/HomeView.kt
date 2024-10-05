package com.example.mywishlistapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.Wish

// Scaffold: structure of the apps (layout)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(navController: NavController, viewModel: WishViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = { AppBarView(title = "Wishlist") },

        floatingActionButton = {
            FabAdd(onClick = {
                navController.navigate(Screen.AddScreen.route + "/-1L")
            })
        }

    ) {
        // get all wishes from database through viewModel
        val wishes = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishes.value, key = { item -> item.id }) { wishItem ->

                // handle dismiss
                val dismissState = rememberDismissState(
                    confirmStateChange = { dismissValue ->
                        if (dismissValue == DismissValue.DismissedToEnd ||
                            dismissValue == DismissValue.DismissedToStart
                        ) {
                            // delete wish after dismiss
                            viewModel.deleteWish(wishItem)
                        }
                        true// = return true
                    }
                )

                // Composable swipe to dismiss
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color: Color =
                            if (dismissState.dismissDirection == DismissDirection.EndToStart) {
                                Color.Red
                            } else Color.Transparent

                        val bgColor by animateColorAsState(targetValue = color, label = "")

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(bgColor)
                                .padding(horizontal = 20.dp), contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                        }
                    },
                    directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                    // the swipe thresholds that will trigger the dismiss state change
                    dismissThresholds = { FractionalThreshold(0.25f) },
                ) {
                    WishItem(wish = wishItem) {
                        val wishId: Long = wishItem.id
                        navController.navigate(Screen.AddScreen.route + "/$wishId")
                    }
                }
            }
        }
    }
}

@Composable
fun FabAdd(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.padding(all = 20.dp),
        contentColor = Color.White,
        backgroundColor = Color.Black,
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add button")
    }
}

@Composable
fun WishItem(wish: Wish, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .clickable { onClick() },
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = wish.title,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = wish.description,
            )
        }
    }
}