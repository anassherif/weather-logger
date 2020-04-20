package com.anas.weatherlogger.features.details.data.repo

import com.anas.weatherlogger.base.data.repo.BaseRepository
import com.anas.weatherlogger.features.details.data.local.DetailsLocalDataSource
import com.anas.weatherlogger.features.main.entities.WeatherEntity

class DetailsRepositoryImpl(private val mDetailsLocalDataSource: DetailsLocalDataSource) :
    BaseRepository(), DetailsRepository {

    /**
     * Get weather details from local storage.
     * @param id
     * @return [WeatherEntity]
     */
    override fun getWeatherDetails(id: Long): WeatherEntity? {
        return mDetailsLocalDataSource.getWeatherDetails(id)
    }

    /**
     * Close database connection.
     */
    override fun onDestroy() {
        mDetailsLocalDataSource.closeConnection()
    }
}