package com.anas.weatherlogger.features.details.domain

import com.anas.weatherlogger.base.domain.BaseUseCase
import com.anas.weatherlogger.features.details.data.repo.DetailsRepository
import com.anas.weatherlogger.features.main.entities.WeatherEntity

class DetailsUseCase(private val mDetailsRepository: DetailsRepository): BaseUseCase() {

    /**
     * Get weather details from remote server or local storage.
     * @param id
     * @return [WeatherEntity]
     */
    fun getWeatherDetails(id: Long): WeatherEntity? {
        return mDetailsRepository.getWeatherDetails(id)
    }

    /**
     * Handle any logic when the screen is onDestroy state.
     */
    override fun onDestroy() {
        mDetailsRepository.onDestroy()
    }
}