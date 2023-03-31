package com.example.sneakersapp.repository

import android.util.Log
import com.example.sneakersapp.data.LocalJsonParsing
import com.example.sneakersapp.model.SneakerViewData
import javax.inject.Inject

class HomeRepository @Inject constructor() {

    fun getTopSneakers(): SneakerViewData {
        return SneakerViewData("ok", LocalJsonParsing.listOfSneakers)
    }

    fun getSearchedItem(input: String) : SneakerViewData {
        val searchedList = LocalJsonParsing.getListOfSearchedString(input)

        if (searchedList.isEmpty()) {
            return SneakerViewData("empty", searchedList)
        }
        return SneakerViewData("ok", searchedList)
    }
}