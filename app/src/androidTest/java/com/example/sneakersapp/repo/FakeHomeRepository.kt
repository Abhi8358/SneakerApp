package com.example.sneakersapp.repo

import android.util.Log
import com.example.sneakersapp.mock.SneakersMock
import com.example.sneakersapp.model.SneakerViewData
import com.example.sneakersapp.repository.HomeRepositoryInterface

class FakeHomeRepository : HomeRepositoryInterface {

    override fun getTopSneakers(): SneakerViewData {
        return SneakerViewData("ok", SneakersMock.getMockListOfSneakers())
    }

    override fun getSearchedItem(input: String): SneakerViewData {
        return SneakerViewData("ok", SneakersMock.getMockListOfSneakers())
    }
}