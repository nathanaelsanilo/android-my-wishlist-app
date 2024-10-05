package com.example.mywishlistapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val wishRepository: WishRepository = Graph.wishRepository): ViewModel() {

    private var _title = mutableStateOf("")
    val title : State<String> = _title

    private var _description = mutableStateOf("")
    val description : State<String> = _description

    // lateinit -> delay the initialization of a variable until we actually define
    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.findAll()
        }
    }

    fun addWish(entity: Wish) {
        // use view model scope because we are calling the suspending function
        // Dispatcher.IO -> choosing which thread that will be used for this coroutine scope
        // can be use to optimize Input/Output operation like creating or reading to/from database
        // You sure that using Dispatchers.IO you don't block the main thread that will lead to
        // poor performance and bad user experience
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.create(entity)
        }
    }

    suspend fun updateWish(entity: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.update(entity)
        }
    }

    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.findById(id)
    }

    suspend fun deleteWish(entity: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.delete(entity)
        }
    }

    fun setTitle(value: String) {
        _title.value = value
    }

    fun setDescription(value: String) {
        _description.value = value
    }

}