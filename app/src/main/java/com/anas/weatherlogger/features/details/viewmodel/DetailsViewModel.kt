package com.anas.weatherlogger.features.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anas.weatherlogger.features.details.domain.DetailsUseCase
import com.anas.weatherlogger.features.main.entities.WeatherEntity
import com.anas.weatherlogger.utils.espresso.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel Layer.
 * It is responsible for handling the coroutines scopes, it doesn't hold any business logic.
 */
class DetailsViewModel(
    private val mDetailsUseCase: DetailsUseCase
) : ViewModel() {

    val weatherDataLiveData = MutableLiveData<WeatherEntity?>()

    /**
     * Get weather details, and update the [weatherDataLiveData] with the data.
     * @param id Id of the weather entity.
     */
    fun getWeatherDetails(id: Long) {
        EspressoIdlingResource.increment()
        viewModelScope.launch(Dispatchers.Default) {
            val weatherEntity = mDetailsUseCase.getWeatherDetails(id)
            weatherDataLiveData.postValue(weatherEntity)
            EspressoIdlingResource.decrement()
        }
    }

    /**
     * Handle any required actions when the screen is onDestroy state.
     */
    fun onDestroy() {
        mDetailsUseCase.onDestroy()
    }
}