package com.anas.weatherlogger.features.main.entities

import androidx.room.Entity

@Entity
data class Coordinates(val lat: Double, val lon: Double)