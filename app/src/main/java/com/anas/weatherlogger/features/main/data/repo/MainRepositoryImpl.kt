package com.anas.weatherlogger.features.main.data.repo

import com.anas.weatherlogger.base.data.repo.BaseRepository
import com.anas.weatherlogger.features.main.data.local.MainLocalDataSource
import com.anas.weatherlogger.features.main.data.remote.MainRemoteDataSource
import com.anas.weatherlogger.features.main.entities.Coordinates
import com.anas.weatherlogger.features.main.entities.WeatherEntity
import java.util.*

/**
 * Repository that is a part of the Data layer and it is used to decide how to get the data,
 * from the remote server or the database.
 *
 * @param mMainLocalDataSource Local Data Source for getting the data from Local Storage.
 * @param mMainRemoteDataSource Remote Data Source for getting the data from Remote server.
 */
class MainRepositoryImpl(
    private val mMainRemoteDataSource: MainRemoteDataSource,
    private val mMainLocalDataSource: MainLocalDataSource
) : BaseRepository(), MainRepository {

    /**
     * Get a weather for the current location from the remote server and store it in the database.
     *
     * @param coordinates The coordinates of the current location.
     *
     * @return A list of [WeatherEntity]
     */
    override suspend fun getWeatherList(coordinates: Coordinates): List<WeatherEntity> {
        val data = mMainRemoteDataSource.getWeatherForCurrentLocation(coordinates)
        saveWeather(WeatherEntity().apply {
            this.main = data.main
            this.date = Date().time
        })
        return getWeatherFromLocal()
    }

    /**
     * Get the Weather List from Database.
     */
    override fun getWeatherFromLocal(): List<WeatherEntity> {
        return mMainLocalDataSource.getWeatherList()
    }

    /**
     * Save a new Weather info in Database.
     */
    override fun saveWeather(weatherEntity: WeatherEntity) {
        mMainLocalDataSource.saveWeather(weatherEntity)
    }

    /**
     * Delete all Weather List from Database.
     */
    override fun deleteAllWeatherData(): Int {
        return mMainLocalDataSource.deleteAllWeatherData()
    }

    /**
     * Close database connection.
     */
    override fun onDestroy() {
        mMainLocalDataSource.closeConnection()
    }
}