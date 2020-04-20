package com.anas.weatherlogger.features.details.data.repo

import com.anas.weatherlogger.features.main.entities.WeatherEntity

interface DetailsRepository {
    fun getWeatherDetails(id: Long): WeatherEntity?
    fun onDestroy()
}