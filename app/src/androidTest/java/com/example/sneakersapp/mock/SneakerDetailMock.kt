package com.example.sneakersapp.mock

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.sneakersapp.dao.SneakerTable

object SneakerDetailMock {

    val MOCK_CONTENT: String = LoremIpsum(20).values.toString()
    const val SNEAKER_SIZE = 7
    const val SNEAKER_COLOR = 1
    const val PRICE_OF_SNEAKER = 1234
    const val IMAGE_URL = "https://image.goat.com/750/attachments/product_template_pictures/images/011/119/994/original/218099_00.png.png"
    const val NAME_OF_SNEAKER = "Sports"
    const val PRODUCT_SLUGS = "Best Ever"
    const val ID = 4353

    // home fragment
    const val TYPE_WORD = "sneaker"

    fun getSneaker(id: Int): SneakerTable {
        return SneakerTable(
            id,
            MOCK_CONTENT,
            PRICE_OF_SNEAKER,
            IMAGE_URL,
            SNEAKER_COLOR,
            SNEAKER_SIZE
        )
    }
}
