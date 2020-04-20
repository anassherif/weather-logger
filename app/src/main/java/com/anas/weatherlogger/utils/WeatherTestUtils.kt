package com.anas.weatherlogger.utils

import com.anas.weatherlogger.features.main.entities.Main
import com.anas.weatherlogger.features.main.entities.WeatherEntity
import com.anas.weatherlogger.features.main.entities.Wind

object WeatherTestUtils {
    fun createWeatherTestEntity(): WeatherEntity {
        return WeatherEntity().apply {
            this.main = Main(
                10.3, 10.2, 12.4,
                5.1, 200.0, 17.5, 2.0
            )
            this.wind = Wind(250.2, 45.0)
        }
    }
}