package com.example.sneakersapp.mock

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.sneakersapp.model.Sneakers

const val ITEMS_IN_LIST = 5

class SneakersMock {

    companion object {

        private val listOfSneakers = ArrayList<Sneakers>()
        private val MOCK_CONTENT: String = LoremIpsum(20).values.toString()
        private const val PRICE_OF_SNEAKER = 1234
        private const val IMAGE_URL = "https://image.goat.com/750/attachments/product_template_pictures/images/011/119/994/original/218099_00.png.png"

        fun getMockListOfSneakers(): ArrayList<Sneakers> {

            for (i:Int in 0..ITEMS_IN_LIST) {

                val imagesUrlList = ArrayList<String>()
                imagesUrlList.add(IMAGE_URL)
                imagesUrlList.add(IMAGE_URL)
                imagesUrlList.add(IMAGE_URL)

                val sneakers = Sneakers(
                    MOCK_CONTENT,
                    MOCK_CONTENT,
                    MOCK_CONTENT,
                    i,
                    PRICE_OF_SNEAKER,
                    imagesUrlList,
                    IMAGE_URL
                )
                listOfSneakers.add(sneakers)
            }
            return listOfSneakers

        }

    }
}