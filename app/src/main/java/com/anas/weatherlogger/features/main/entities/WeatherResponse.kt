package com.anas.weatherlogger.features.main.entities

import java.io.Serializable

data class WeatherResponse(val coordinates: Coordinates, val main: Main) : Serializable