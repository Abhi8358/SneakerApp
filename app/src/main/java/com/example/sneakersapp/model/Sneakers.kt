package com.example.sneakersapp.model

data class Sneakers(
    val brandName: String,
    val productName: String,
    val productSlugs: String,
    val id: Int,
    val price: Int,
    val imagesUrlList: ArrayList<String>,
    val gridPictureUrl: String
)