package com.anas.weatherlogger.features.details.data.local

import com.anas.weatherlogger.base.data.local.LocalDataSource
import com.anas.weatherlogger.base.data.room.WeatherRoomDatabase
import com.anas.weatherlogger.features.main.entities.WeatherEntity

class DetailsLocalDataSource(private val mWeatherRoomDatabase: WeatherRoomDatabase) :
    LocalDataSource() {

    /**
     * Get weather details by ID from database.
     * @param id
     * @return [WeatherEntity]
     */
    fun getWeatherDetails(id: Long): WeatherEntity? {
        return mWeatherRoomDatabase.weatherDAO().getWeatherById(id)
    }

    /**
     * Close the database.
     */
    override fun closeConnection() {
        if (mWeatherRoomDatabase.isOpen)
            mWeatherRoomDatabase.close()
    }
}