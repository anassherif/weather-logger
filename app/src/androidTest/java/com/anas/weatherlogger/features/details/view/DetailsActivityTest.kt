package com.anas.weatherlogger.features.details.view

import android.content.Intent
import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.anas.weatherlogger.R
import com.anas.weatherlogger.utils.constants.Constants
import com.anas.weatherlogger.utils.espresso.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailsActivityTest {

    private val weatherIdUnderTest = 1L

    @get:Rule
    val detailsActivity = ActivityTestRule(
        DetailsActivity::class.java,
        false, false
    )

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResources)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResources)
    }

    @Test
    fun isDetailsDisplayed_onActivityLaunch() {
        val bundle = Bundle().apply { putLong(Constants.WEATHER_ID, weatherIdUnderTest) }
        val intent = Intent().apply { putExtra(Constants.BUNDLE_DATA, bundle) }

        detailsActivity.launchActivity(intent)
        onView(withId(R.id.cvDetails)).check(matches(isDisplayed()))
    }
}