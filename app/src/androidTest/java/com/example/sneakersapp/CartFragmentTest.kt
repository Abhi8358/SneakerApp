package com.example.sneakersapp

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.sneakersapp.hilt.launchFragmentInHiltContainer
import com.example.sneakersapp.ui.fragments.CartFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@HiltAndroidTest
class CartFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val navController = Mockito.mock(NavController::class.java)

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun verifyCartFragment() {

        launchFragmentInHiltContainer<CartFragment>( null, R.style.Theme_SneakersApp)

        // main container
        onView(withId(R.id.cart_container)).check(matches(isDisplayed()))

        // toolbar
        onView(withId(R.id.cart_fragment_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.back_button)).check(matches(isDisplayed()))
        onView(withId(R.id.back_button)).check(matches(isClickable()))
        onView(withId(R.id.toolbar_text)).check(matches(isDisplayed()))

        // recycler view
        onView(withId(R.id.cart_recycler_view)).check(matches(isDisplayed()))

        //cart Bill Container
        onView(withId(R.id.cart_bill_container)).check(matches(isDisplayed()))
        onView(withId(R.id.cart_bill_container))
            .check(isCompletelyBelow(withId(R.id.cart_recycler_view)))
            .check(isCompletelyAbove(withId(R.id.checkout_container)))
        onView(withId(R.id.sub_totals)).check(matches(isDisplayed()))
        onView(withId(R.id.sub_totals)).check(matches(isDisplayed()))
        onView(withId(R.id.text_charges)).check(matches(isDisplayed()))

    }

    @Test
    fun verifyAndTestCheckoutContainer() {

        launchFragmentInHiltContainer<CartFragment>( null, R.style.Theme_SneakersApp) {
            navController.setGraph(R.navigation.sneaker_navigation_graph)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.checkout_container)).check(matches(isDisplayed()))
        onView(withId(R.id.checkout_container))
            .check(isCompletelyBelow(withId(R.id.cart_bill_container)))
        onView(withId(R.id.total_price)).check(matches(isDisplayed()))
        onView(withId(R.id.checkout)).check(matches(isDisplayed()))
        onView(withId(R.id.checkout)).check(matches(isClickable()))

        // click on checkout button
        onView(withId(R.id.checkout)).perform(click())
    }

   private fun withRecyclerView(recyclerViewId: Int = -1, count: Int = 0): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId, count)
   }
}
