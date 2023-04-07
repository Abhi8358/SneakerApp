package com.example.sneakersapp.repository

import androidx.lifecycle.LiveData
import com.example.sneakersapp.dao.CartDataAccessObject
import com.example.sneakersapp.dao.CartDataBase
import com.example.sneakersapp.dao.SneakerTable
import javax.inject.Inject

class CartRepository @Inject constructor(private val dao: CartDataAccessObject) : CartRepositoryInterface{

     override fun removeItemFromCart(sneakerTable: SneakerTable) {
        return dao.delete(sneakerTable)
    }

    override fun getAllItem(): LiveData<List<SneakerTable>> {
        return dao.getAllItem()
    }

     override fun getTotalCardPrice(): Int {
        return dao.totalPriceOfCartItem()
    }

    override suspend fun deleteAllItemsFromCart() {
        return dao.deleteAllItemFromCart()
    }
}