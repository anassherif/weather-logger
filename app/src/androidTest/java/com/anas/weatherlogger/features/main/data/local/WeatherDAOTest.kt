package com.anas.weatherlogger.features.main.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anas.weatherlogger.base.data.room.WeatherRoomDatabase
import com.anas.weatherlogger.utils.WeatherTestUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class WeatherDAOTest : KoinTest {

    private val weatherDoa: WeatherDAO by inject()

    @Before
    fun setUp() {
        loadKoinModules(databaseModule)
    }

    @Test
    fun testSaveWeather() {
        // Given
        val weatherEntity = WeatherTestUtils.createWeatherTestEntity()

        // When
        val rowId = weatherDoa.saveWeather(weatherEntity)
        val retrievedWeatherEntity = weatherDoa.getWeatherById(rowId)

        // Then
        Assert.assertEquals(retrievedWeatherEntity.id, rowId)
    }

    @Test
    fun testDeleteAllRows() {
        // When
        weatherDoa.deleteAllRows()
        val weatherList = weatherDoa.getWeatherList()

        // Assert
        Assert.assertEquals(weatherList.size, 0)
    }

    private val databaseModule = module(override = true) {
        single {
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                WeatherRoomDatabase::class.java
            )
        }
        single { get<WeatherRoomDatabase>().weatherDAO() }
    }
}