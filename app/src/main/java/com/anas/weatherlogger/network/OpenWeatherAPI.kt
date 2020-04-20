package com.anas.weatherlogger.network

import com.anas.weatherlogger.features.main.entities.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is the interface that contains all the OpenWeather APIs
 */
interface OpenWeatherAPI {

    @GET("weather")
    suspend fun getWeatherForCurrentLocation(
        @Query("lat") lat: Double, @Query("lon") lon: Double
    ): WeatherResponse
}