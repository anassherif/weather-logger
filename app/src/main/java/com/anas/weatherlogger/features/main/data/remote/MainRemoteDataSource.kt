package com.anas.weatherlogger.features.main.data.remote

import com.anas.weatherlogger.base.data.remote.RemoteDataSource
import com.anas.weatherlogger.features.main.entities.Coordinates
import com.anas.weatherlogger.features.main.entities.WeatherResponse
import com.anas.weatherlogger.network.NetworkRetrofit
import com.anas.weatherlogger.network.OpenWeatherAPI

/**
 * Remote Data Source that is used to request the data from the remote server.
 */
class MainRemoteDataSource : RemoteDataSource() {

    /**
     * Get the weather for the current location from the remote server.
     * It is a suspend function so that it can be used with Coroutines.
     *
     * @param coordinates
     * @return [WeatherResponse]
     */
    suspend fun getWeatherForCurrentLocation(coordinates: Coordinates): WeatherResponse {
        return NetworkRetrofit().getInstance(OpenWeatherAPI::class.java)
            .getWeatherForCurrentLocation(coordinates.lat, coordinates.lon)
    }
}