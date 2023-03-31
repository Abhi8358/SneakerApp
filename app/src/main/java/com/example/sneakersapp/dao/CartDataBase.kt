package com.example.sneakersapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SneakerTable::class],
    version = 1
)
abstract class CartDataBase : RoomDatabase() {
    abstract fun getCartDao(): CartDataAccessObject
}
