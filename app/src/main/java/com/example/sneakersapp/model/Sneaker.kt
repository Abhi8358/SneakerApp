package com.example.sneakersapp.model

class Sneaker(
    val brandName: String?,
    val productName: String?,
    val productSlugs: String?,
    val id: Int,
    val price: Int,
    val imagesUrlList: ArrayList<String>?
) {
    data class Builder(
        var brandName: String? = null,
        var productName: String? = null,
        var productSlugs: String? = null,
        var id: Int = 0,
        var price: Int = 0,
        var imagesUrlList: ArrayList<String>? = null) {

        fun brandName(brandName: String) = apply { this.brandName = brandName }
        fun productName(productName: String) = apply { this.productName = productName }
        fun productSlugs(productSlugs: String) = apply { this.productSlugs = productSlugs }
        fun id(id: Int) = apply { this.id = id }
        fun price(price: Int) = apply { this.price = price }
        fun imagesUrlList(imagesUrlList: ArrayList<String>) = apply { this.imagesUrlList = imagesUrlList }
        fun build() = Sneaker(brandName, productName, productSlugs, id, price, imagesUrlList)
    }
}