package com.example.sneakersapp

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.sneakersapp.hilt.launchFragmentInHiltContainer
import com.example.sneakersapp.mock.SneakerDetailMock

import com.example.sneakersapp.ui.fragments.SneakerDetailFragment
import com.example.sneakersapp.utils.Constants.Companion.ID
import com.example.sneakersapp.utils.Constants.Companion.IMAGE_URLS
import com.example.sneakersapp.utils.Constants.Companion.NAME_OF_SNEAKER
import com.example.sneakersapp.utils.Constants.Companion.PRICE_OF_SNEAKER
import com.example.sneakersapp.utils.Constants.Companion.PRODUCT_SLUGS
import org.junit.Test

class SneakerDetailFragmentTest {

    @Test
    fun verifySneakerDetailFragmentScreen() {
        val bundle = SneakerDetailFragmentBundle()
        launchFragmentInHiltContainer<SneakerDetailFragment>( bundle, R.style.Theme_SneakersApp)

        onView(withId(R.id.sneaker_detail_container)).check(matches(isDisplayed()))

        // toolbar
        onView(withId(R.id.detail_fragment_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.back_button)).check(matches(isDisplayed()))
        onView(withId(R.id.back_button)).check(matches(isClickable()))

        onView(withId(R.id.view_pager))
            .check(matches(isCompletelyDisplayed()))
            .check(isCompletelyBelow(withId(R.id.detail_fragment_toolbar)))
            .check(isCompletelyAbove(withId(R.id.sneaker_name)))

        onView(withId(R.id.sneaker_name))
            .check(matches(isCompletelyDisplayed()))
            .check(isCompletelyBelow(withId(R.id.view_pager)))
            .check(isCompletelyAbove(withId(R.id.slugs_name)))

        onView(withId(R.id.slugs_name))
            .check(matches(isCompletelyDisplayed()))
            .check(isCompletelyBelow(withId(R.id.sneaker_name)))
            .check(isCompletelyAbove(withId(R.id.size_view_container)))

        onView(withId(R.id.size_view_container))
            .check(matches(isCompletelyDisplayed()))
            .check(isCompletelyBelow(withId(R.id.slugs_name)))
            .check(isCompletelyAbove(withId(R.id.color_view_container)))

        onView(withId(R.id.color_view_container))
            .check(matches(isCompletelyDisplayed()))
            .check(isCompletelyBelow(withId(R.id.size_view_container)))
            .check(isCompletelyAbove(withId(R.id.pirce_add_to_cart_container)))

        onView(withId(R.id.pirce_add_to_cart_container))
            .check(matches(isCompletelyDisplayed()))
            .check(isCompletelyBelow(withId(R.id.color_view_container)))
    }

    private fun SneakerDetailFragmentBundle(): Bundle {
        val bundle = Bundle()
        bundle.putInt(PRICE_OF_SNEAKER, SneakerDetailMock.PRICE_OF_SNEAKER)
        bundle.putStringArrayList(IMAGE_URLS, arrayListOf(SneakerDetailMock.IMAGE_URL))
        bundle.putString(NAME_OF_SNEAKER, SneakerDetailMock.NAME_OF_SNEAKER)
        bundle.putString(PRODUCT_SLUGS, SneakerDetailMock.PRODUCT_SLUGS)
        bundle.putInt(ID, SneakerDetailMock.ID)
        return bundle
    }
}
