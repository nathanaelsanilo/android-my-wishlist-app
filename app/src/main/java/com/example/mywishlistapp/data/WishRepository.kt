package com.example.mywishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun create(entity: Wish) {
        wishDao.addWish(entity)
    }

    // shorthand for defining the function
    fun findAll(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun findById(id: Long): Flow<Wish> {
        return wishDao.getWish(id)
    }

    suspend fun update(entity: Wish) {
        wishDao.updateWish(entity)
    }

    suspend fun delete(entity: Wish) {
        wishDao.deleteWish(entity)
    }
}