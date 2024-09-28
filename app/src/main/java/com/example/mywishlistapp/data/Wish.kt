package com.example.mywishlistapp.data

data class Wish(
    val id: Long = 0L,
    val title: String = "",
    val description: String = ""
)

// Congrats, you found new keyword!
// object declarations
// allows you to define a class and create an instance of it in a single step
object WishDummy {
    val wishes = listOf<Wish>(
        Wish(
            id = 1,
            title = "Lorem ipsum",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
        Wish(
            id = 2,
            title = "Lorem ipsum",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
        Wish(
            id = 2,
            title = "Lorem ipsum",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
    )
}