package com.example.mywishlistapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mywishlistapp.data.Wish

// Scaffold: structure of the apps (layout)
@Composable
fun HomeView() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBarView("Wishlist", {
                Toast.makeText(context, "Back Navigation Works!", Toast.LENGTH_LONG).show()
            })
        },

        floatingActionButton = {
            FabAdd(context)
        }

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

        }
    }
}

@Composable
fun FabAdd(context: Context) {
    FloatingActionButton(
        onClick = {
            Toast.makeText(context, "FAB Works!", Toast.LENGTH_LONG).show()
        },
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