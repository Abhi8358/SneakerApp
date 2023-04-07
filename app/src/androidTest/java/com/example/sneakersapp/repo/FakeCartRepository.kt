package com.example.sneakersapp.repo

import androidx.lifecycle.LiveData
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.repository.CartRepositoryInterface

class FakeCartRepository : CartRepositoryInterface {
    override fun removeItemFromCart(sneakerTable: SneakerTable) {
        TODO("Not yet implemented")
    }

    override fun getAllItem(): LiveData<List<SneakerTable>> {
        TODO("Not yet implemented")
    }

    override fun getTotalCardPrice(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllItemsFromCart() {
        TODO("Not yet implemented")
    }
}