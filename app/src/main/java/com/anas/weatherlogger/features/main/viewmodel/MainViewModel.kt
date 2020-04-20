package com.anas.weatherlogger.features.main.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anas.weatherlogger.features.main.LocationLiveData
import com.anas.weatherlogger.features.main.domain.MainUseCase
import com.anas.weatherlogger.features.main.entities.Coordinates
import com.anas.weatherlogger.features.main.entities.WeatherEntity
import com.anas.weatherlogger.utils.espresso.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel Layer.
 * It is responsible for handling the coroutines scopes, it doesn't hold any business logic.
 */
class MainViewModel(
    context: Context,
    private val mMainUseCase: MainUseCase
) : ViewModel() {

    val weatherListLiveData = MutableLiveData<List<WeatherEntity>>()
    val deleteWeatherLiveData = MutableLiveData<Int>()
    val currentLocationLiveData = LocationLiveData(context)

    /**
     * Get current location, and update the [currentLocationLiveData] with the location coordinates.
     */
    fun getCurrentLocation() {
        currentLocationLiveData.getCurrentLocation()
    }

    /**
     * Get Weather list.
     * Update the [weatherListLiveData] after retrieving the data to update the view.
     * @param coordinates Coordinates of the current location.
     */
    fun getWeatherList(coordinates: Coordinates) {
        EspressoIdlingResource.increment()
        viewModelScope.launch(Dispatchers.Default) {
            val weatherList = mMainUseCase.getWeatherList(coordinates)
            weatherListLiveData.postValue(weatherList)
            EspressoIdlingResource.decrement()
        }
    }

    /**
     * Delete all Weather list.
     * Update the [deleteWeatherLiveData] after retrieving the data to update the view.
     */
    fun deleteAllWeatherData() {
        viewModelScope.launch(Dispatchers.Default) {
            val rowCount = mMainUseCase.deleteAllWeatherData()
            deleteWeatherLiveData.postValue(rowCount)
        }
    }

    /**
     * Handle any required actions when the screen is onDestroy state.
     */
    fun onDestroy() {
        mMainUseCase.onDestroy()
    }
}