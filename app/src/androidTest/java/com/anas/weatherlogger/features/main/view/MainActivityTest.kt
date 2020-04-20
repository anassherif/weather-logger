package com.anas.weatherlogger.features.main.view

import android.os.Build
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.anas.weatherlogger.R
import com.anas.weatherlogger.base.view.adapter.BaseViewHolder
import com.anas.weatherlogger.utils.RecyclerViewAssertion.Companion.withItemCount
import com.anas.weatherlogger.utils.espresso.EspressoIdlingResource
import org.hamcrest.Matchers.greaterThan
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val commandPrefix = "appops set "
    private val commandPostfix = " android:mock_location allow"

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResources)

        // This is needed to allow location mocking.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            with(getInstrumentation().uiAutomation) {
                executeShellCommand(commandPrefix + InstrumentationRegistry.getInstrumentation().targetContext.packageName + commandPostfix)
                Thread.sleep(1000)
            }
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResources)
    }

    @Test
    fun isDataDisplayed_onMenuSaveButtonClicked() {
        onView(withId(R.id.menu_save)).perform(click())
        onView(withId(R.id.rvData)).check(matches(isDisplayed()))
        onView(withId(R.id.rvData)).check(withItemCount(greaterThan(0)))
    }

    @Test
    fun isDetailsScreenVisible_onItemClicked() {
        onView(withId(R.id.menu_save)).perform(click())
        onView(withId(R.id.rvData)).perform(actionOnItemAtPosition<BaseViewHolder>(0, click()))
    }

    @Test
    fun isAllDataDeleted_onDeleteButtonClicked() {
        onView(withId(R.id.menu_save)).perform(click())
        onView(withId(R.id.btnClearAllData)).perform(click())
        onView(withId(R.id.rvData)).check(withItemCount(0))
    }

    @Test
    fun isHomeVisible_onBackPressedFromDetailsPage(){
        onView(withId(R.id.menu_save)).perform(click())
        onView(withId(R.id.rvData)).perform(actionOnItemAtPosition<BaseViewHolder>(0, click()))
        pressBack()
        onView(withId(R.id.rvData)).check(matches(isDisplayed()))
    }
}