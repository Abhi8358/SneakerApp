package com.example.sneakersapp

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.sneakersapp.hilt.launchFragmentInHiltContainer
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.LayoutAssertions.noEllipsizedText
import androidx.test.espresso.assertion.LayoutAssertions.noOverlaps
import com.example.sneakersapp.mock.SneakerDetailMock.TYPE_WORD
import com.example.sneakersapp.testUtils.RecyclerViewMatcher
import com.example.sneakersapp.ui.fragments.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun verifySneakerDetailScreen() {

        launchFragmentInHiltContainer<HomeFragment>( null, R.style.Theme_SneakersApp)

        onView(withId(R.id.home_fragment_container)).check(matches(isDisplayed()))

        // toolbar
        onView(withId(R.id.home_fragment_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.home_text)).check(matches(isDisplayed()))
        onView(withId(R.id.search_box)).check(matches(isDisplayed()))

        onView(withId(R.id.category)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.category))
            .check(isCompletelyBelow(withId(R.id.home_fragment_toolbar)))
            .check(isCompletelyAbove(withId(R.id.home_fragment_recycler_view)))
        onView(withId(R.id.category))
            .check(noEllipsizedText())

        onView(withId(R.id.home_fragment_recycler_view)).check(matches(isDisplayed()))
        onView(withId(R.id.home_fragment_recycler_view))
            .check(isCompletelyBelow(withId(R.id.category)))
            .check(noOverlaps())

        onView(withId(R.id.search_box))
            .perform(typeText(TYPE_WORD))
            .check(matches(withText(TYPE_WORD)))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun verifyRecyclerViewActions() {
        launchFragmentInHiltContainer<HomeFragment>( null, R.style.Theme_App) {
           // homeViewModel = this@HomeFragmentTest.homeViewModel
            TestCase.assertNotNull(requireActivity())
            val navController = TestNavHostController(requireActivity())
            requireActivity().runOnUiThread { navController.setGraph(R.navigation.sneaker_navigation_graph) }
            navController.setCurrentDestination(R.id.homeFragment)
            Navigation.setViewNavController(requireView(), navController)

            val destination = navController.currentDestination
            assertNotNull(destination)
            assertEquals(destination!!.id, R.id.homeFragment)
        }

        onView(withId(R.id.home_fragment_recycler_view)).check(matches(isDisplayed()))
        onView(withRecyclerView(R.id.home_fragment_recycler_view).atPosition(0))
            .perform(click())

    }

    private fun withRecyclerView(recyclerViewId: Int = -1, count: Int = 0): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId, count)
    }
}
