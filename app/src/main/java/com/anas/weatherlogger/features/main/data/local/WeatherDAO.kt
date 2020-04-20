package com.anas.weatherlogger.features.main.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anas.weatherlogger.features.main.entities.WeatherEntity

@Dao
interface WeatherDAO {

    @Insert(entity = WeatherEntity::class)
    fun saveWeather(weatherEntity: WeatherEntity): Long

    @Query("SELECT * FROM weatherEntity ORDER BY date DESC")
    fun getWeatherList(): List<WeatherEntity>

    @Query("SELECT * FROM weatherEntity WHERE id =:id")
    fun getWeatherById(id: Long): WeatherEntity

    @Query("DELETE FROM weatherEntity")
    fun deleteAllRows(): Int
}