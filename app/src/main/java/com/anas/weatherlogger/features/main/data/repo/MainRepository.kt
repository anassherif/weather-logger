package com.anas.weatherlogger.features.main.data.repo

import com.anas.weatherlogger.features.main.entities.Coordinates
import com.anas.weatherlogger.features.main.entities.WeatherEntity

interface MainRepository {
    suspend fun getWeatherList(coordinates: Coordinates): List<WeatherEntity>
    fun getWeatherFromLocal(): List<WeatherEntity>
    fun saveWeather(weatherEntity: WeatherEntity)
    fun deleteAllWeatherData(): Int
    fun onDestroy()
}