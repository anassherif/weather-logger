package com.anas.weatherlogger.utils.espresso

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {


    @JvmStatic val countingIdlingResources = CountingIdlingResource("Resource")


    fun increment() {
        countingIdlingResources.increment()
    }

    fun decrement() {
        if (!countingIdlingResources.isIdleNow)
            countingIdlingResources.decrement()
    }
}