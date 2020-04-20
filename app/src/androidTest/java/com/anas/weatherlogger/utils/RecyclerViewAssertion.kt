package com.anas.weatherlogger.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.Matcher
import org.hamcrest.core.Is

/**
 * Custom ViewAssertion class for recyclerView that can help you to check the itemCount of the recyclerView.
 */
class RecyclerViewAssertion(private val matcher: Matcher<Int>) : ViewAssertion {
    companion object {
        fun withItemCount(expectedCount: Int): ViewAssertion {
            return withItemCount(Is.`is`(expectedCount))
        }

        fun withItemCount(matcher: Matcher<Int>): ViewAssertion {
            return RecyclerViewAssertion(matcher)
        }
    }

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter!!.itemCount, matcher)
    }
}
