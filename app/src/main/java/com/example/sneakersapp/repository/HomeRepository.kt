package com.example.sneakersapp.repository

import android.util.Log
import com.example.sneakersapp.data.LocalJsonParsing
import com.example.sneakersapp.model.SneakerViewData
import javax.inject.Inject

class HomeRepository @Inject constructor() {

    fun getTopSneakers(): SneakerViewData {
        Log.d("Abhishek","List Size = " + LocalJsonParsing.listOfSneakers.size)
        return SneakerViewData("ok", LocalJsonParsing.listOfSneakers)
    }
}