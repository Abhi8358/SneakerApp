package com.example.sneakersapp

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.example.sneakersapp.hilt.launchFragmentInHiltContainer
import com.example.sneakersapp.mock.SneakerDetailMock
import com.example.sneakersapp.ui.fragments.SneakerDetailFragment
import com.example.sneakersapp.utils.Constants.Companion.ID
import com.example.sneakersapp.utils.Constants.Companion.IMAGE_URLS
import com.example.sneakersapp.utils.Constants.Companion.NAME_OF_SNEAKER
import com.example.sneakersapp.utils.Constants.Companion.PRICE_OF_SNEAKER
import com.example.sneakersapp.utils.Constants.Companion.PRODUCT_SLUGS
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@MediumTest
class SneakerDetailFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        hiltRule.inject()
    }

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

    @Test
    fun verifyAndClickOnAddToCart() {
        val bundle = SneakerDetailFragmentBundle()

        launchFragmentInHiltContainer<SneakerDetailFragment>( bundle, R.style.Theme_SneakersApp) {

            assertNotNull(requireActivity())
            val navController = TestNavHostController(requireActivity())

            requireActivity().runOnUiThread { navController.setGraph(R.navigation.sneaker_navigation_graph) }
            navController.setCurrentDestination(R.id.sneakerDetailFragment)
            Navigation.setViewNavController(requireView(), navController)

           // navController.navigate(R.id.action_sneakerDetailFragment_to_cartFragment)
            val destination = navController.currentDestination
            assertNotNull(destination)
            assertEquals(destination!!.id, R.id.sneakerDetailFragment)
        }

        onView(withId(R.id.pirce_add_to_cart_container))
            .check(matches(isCompletelyDisplayed()))
            .check(isCompletelyBelow(withId(R.id.color_view_container)))

        onView(withId(R.id.add_to_cart))
            .check(matches(isCompletelyDisplayed()))
            .check(matches(isClickable()))
            .perform(scrollTo(), click())
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
