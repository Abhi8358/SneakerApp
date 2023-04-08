package com.example.sneakersapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.mock.SneakerDetailMock.PRICE_OF_SNEAKER
import com.example.sneakersapp.mock.SneakersMock
import com.example.sneakersapp.repository.CartRepositoryInterface

class FakeCartRepository : CartRepositoryInterface {
    override fun removeItemFromCart(sneakerTable: SneakerTable) {
        SneakersMock.sneakerTableList.remove(SneakersMock.sneakerTableList[0])
    }

    override fun getAllItem(): LiveData<List<SneakerTable>> {
        SneakersMock.getMockListOfSneakerTable()
        val liveData = MutableLiveData<List<SneakerTable>>()
        liveData.value = SneakersMock.sneakerTableList
        return liveData
    }

    override fun getTotalCardPrice(): Int {
        return PRICE_OF_SNEAKER*10
    }

    override suspend fun deleteAllItemsFromCart() {
        SneakersMock.sneakerTableList.clear()
    }
}