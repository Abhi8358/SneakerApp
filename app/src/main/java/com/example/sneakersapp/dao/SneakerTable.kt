package com.example.sneakersapp.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "sneakerCart"
)
data class SneakerTable(
    @PrimaryKey val id: Int,
    val brandName: String,
    val price: Int,
    val gridPictureUrl: String,
    val color: Int,
    val size: Int
)