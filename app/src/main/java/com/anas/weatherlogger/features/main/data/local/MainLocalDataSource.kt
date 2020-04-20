package com.anas.weatherlogger.features.main.data.local

import com.anas.weatherlogger.base.data.local.LocalDataSource
import com.anas.weatherlogger.base.data.room.WeatherRoomDatabase
import com.anas.weatherlogger.features.main.entities.WeatherEntity

/**
 * Local Data Source for the Main screen.
 *
 * @param mWeatherRoomDatabase The Room database instance that stores the data locally.
 * */
class MainLocalDataSource(private val mWeatherRoomDatabase: WeatherRoomDatabase) : LocalDataSource() {

    /**
     * Get WeatherList from database.
     *
     * @return List of [WeatherEntity].
     * */
    fun getWeatherList(): List<WeatherEntity> {
        return mWeatherRoomDatabase.weatherDAO().getWeatherList()
    }

    /**
     * Save a new WeatherEntity in database.
     *
     * @param weatherEntity
     */
    fun saveWeather(weatherEntity: WeatherEntity) {
        mWeatherRoomDatabase.weatherDAO().saveWeather(weatherEntity)
    }

    /**
     * Delete all the rows from database table.
     *
     * @return number of rows affected by this query.
     */
    fun deleteAllWeatherData(): Int {
        return mWeatherRoomDatabase.weatherDAO().deleteAllRows()
    }

    /**
     * Close the database.
     */
    override fun closeConnection() {
        if (mWeatherRoomDatabase.isOpen)
            mWeatherRoomDatabase.close()
    }
}