package com.anas.weatherlogger.features.main.domain

import com.anas.weatherlogger.base.domain.BaseUseCase
import com.anas.weatherlogger.features.main.data.repo.MainRepository
import com.anas.weatherlogger.features.main.entities.Coordinates
import com.anas.weatherlogger.features.main.entities.WeatherEntity

/**
 * Use case layer that holds all the business logic.
 *
 * @param mMainRepository Repository that is a part of the Data layer that is responsible for
 * getting and storing the data.
 */
class MainUseCase(
    private val mMainRepository: MainRepository
) : BaseUseCase() {

    /**
     * Call the repo to get the Weather list.
     */
    suspend fun getWeatherList(coordinates: Coordinates): List<WeatherEntity> {
        return mMainRepository.getWeatherList(coordinates)
    }

    /**
     * Call the repo to delete all Weather list.
     */
    fun deleteAllWeatherData(): Int {
        return mMainRepository.deleteAllWeatherData()
    }

    /**
     * Handle any logic when the screen is onDestroy state.
     */
    override fun onDestroy() {
        mMainRepository.onDestroy()
    }
}