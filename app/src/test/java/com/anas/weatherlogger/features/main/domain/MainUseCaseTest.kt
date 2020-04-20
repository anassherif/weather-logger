package com.anas.weatherlogger.features.main.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anas.weatherlogger.features.main.data.repo.MainRepository
import com.anas.weatherlogger.features.main.entities.Coordinates
import com.anas.weatherlogger.features.main.entities.Main
import com.anas.weatherlogger.features.main.entities.WeatherEntity
import com.anas.weatherlogger.features.main.entities.Wind
import com.anas.weatherlogger.utils.WeatherTestUtils
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainUseCaseTest : KoinTest {

    private val mainUseCase: MainUseCase by inject()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(mainModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun getWeatherList() = runBlockingTest {
        // Given
        val coordinates = Coordinates(30.0000632, 31.1457396)
        val weatherEntity = WeatherTestUtils.createWeatherTestEntity()
        val expected = listOf(weatherEntity)

        // When
        declareMock<MainRepository> {
            given(this.getWeatherList(coordinates)).willReturn(expected)
        }
        val weatherList = mainUseCase.getWeatherList(coordinates)

        // Then
        Assert.assertEquals(expected.toString(), weatherList.toString())
    }

    @Test
    fun deleteAllWeatherData() {
        // When
        declareMock<MainRepository> {
            given(this.deleteAllWeatherData()).willReturn(0)
        }
        val numOfRows = mainUseCase.deleteAllWeatherData()

        // Then
        Assert.assertEquals(0, numOfRows)
    }

    private val mainModule = module {
        factory { MainUseCase(get()) }
    }
}