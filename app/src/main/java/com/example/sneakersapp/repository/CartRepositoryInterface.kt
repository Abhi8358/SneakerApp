package com.example.sneakersapp.repository

import androidx.lifecycle.LiveData
import com.example.sneakersapp.dao.SneakerTable

interface CartRepositoryInterface {

    fun removeItemFromCart(sneakerTable: SneakerTable)

    fun getAllItem(): LiveData<List<SneakerTable>>

    fun getTotalCardPrice(): Int

    suspend fun deleteAllItemsFromCart()
}