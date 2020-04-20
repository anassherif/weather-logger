package com.anas.weatherlogger.features.main

import android.content.Context
import androidx.lifecycle.LiveData
import com.anas.weatherlogger.features.main.entities.Coordinates
import com.anas.weatherlogger.utils.espresso.EspressoIdlingResource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationLiveData(context: Context) : LiveData<Coordinates?>() {
    private var fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun getCurrentLocation() {
        EspressoIdlingResource.increment()
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if (it != null) {
                EspressoIdlingResource.decrement()
                value = Coordinates(it.latitude, it.longitude)
            } else {
                value = null
            }
        }
    }
}