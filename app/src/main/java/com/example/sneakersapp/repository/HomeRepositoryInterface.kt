package com.example.sneakersapp.repository

import com.example.sneakersapp.model.SneakerViewData

interface HomeRepositoryInterface {

    fun getTopSneakers(): SneakerViewData

    fun getSearchedItem(input: String) : SneakerViewData
}