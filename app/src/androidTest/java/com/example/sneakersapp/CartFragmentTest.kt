package com.example.sneakersapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sneakersapp.hilt.launchFragmentInHiltContainer
import com.example.sneakersapp.ui.fragments.CartFragment
import android.widget.AdapterView
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CartFragmentTest {

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

        onView(withRecyclerView(recyclerViewId = R.id.cart_recycler_view).atPosition(0))
            .check(matches(hasDescendant(withId(R.id.cross_button)))).check(matches(isDisplayed()))

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

        launchFragmentInHiltContainer<CartFragment>( null, R.style.Theme_SneakersApp)

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
