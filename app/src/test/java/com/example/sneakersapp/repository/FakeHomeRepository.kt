package com.example.sneakersapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.sneakersapp.data.LocalJsonParsing
import com.example.sneakersapp.mock.SneakersMock
import com.example.sneakersapp.model.SneakerViewData
import com.example.sneakersapp.model.Sneakers

class FakeHomeRepository : HomeRepositoryInterface{

    private val sneakers = mutableListOf<Sneakers>()
    private val sneakersLiveData = MutableLiveData<List<Sneakers>>(sneakers)

    override fun getTopSneakers(): SneakerViewData {
        return SneakerViewData("ok", SneakersMock.getMockListOfSneakers())
    }

    override fun getSearchedItem(input: String): SneakerViewData {
        TODO("Not yet implemented")
    }

    private fun refreshLiveData() {
        sneakersLiveData.postValue(sneakers)
    }
}