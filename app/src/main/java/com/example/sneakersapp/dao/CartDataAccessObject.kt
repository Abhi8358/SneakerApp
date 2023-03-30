package com.example.sneakersapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDataAccessObject {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(sneakers: SneakerTable) : Long
    @Query("SELECT * FROM sneakerCart")
    fun getAllItem(): LiveData<List<SneakerTable>>

    @Delete
    fun delete(sneakers: SneakerTable)
    @Query("DELETE FROM sneakerCart")
    suspend fun deleteAllItemFromCart()

    @Query("SELECT SUM(price) FROM sneakerCart")
    fun totalPriceOfCartItem(): Int
}