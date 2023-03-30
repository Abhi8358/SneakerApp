package com.example.sneakersapp.repository

import androidx.lifecycle.LiveData
import com.example.sneakersapp.dao.CartDataAccessObject
import com.example.sneakersapp.dao.CartDataBase
import com.example.sneakersapp.dao.SneakerTable
import javax.inject.Inject

class CartRepository @Inject constructor(private val dao: CartDataAccessObject) {

     fun removeItemFromCart(sneakerTable: SneakerTable) {
        return dao.delete(sneakerTable)
    }

    fun getAllItem(): LiveData<List<SneakerTable>> {
        return dao.getAllItem()
    }

     fun getTotalCardPrice(): Int {
        return dao.totalPriceOfCartItem()
    }

    suspend fun deleteAllItemsFromCart() {
        return dao.deleteAllItemFromCart()
    }
}