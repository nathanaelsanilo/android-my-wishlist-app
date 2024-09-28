package com.example.mywishlistapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WishViewModel: ViewModel() {

    private var _title = mutableStateOf("")
    val title : State<String> = _title

    private var _description = mutableStateOf("")
    val description : State<String> = _description

    fun setTitle(value: String) {
        _title.value = value
    }

    fun setDescription(value: String) {
        _description.value = value
    }

}