package com.example.sneakersapp.repository

import com.example.sneakersapp.dao.CartDataAccessObject
import com.example.sneakersapp.dao.SneakerTable
import javax.inject.Inject

class SneakerDetailRepository @Inject constructor(private val dao: CartDataAccessObject) {

    suspend fun upsetSneaker(sneakerTable: SneakerTable): Long {
        return dao.upsert(sneakerTable)
    }
}