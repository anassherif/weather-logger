package com.anas.weatherlogger.app

import androidx.multidex.MultiDexApplication
import com.anas.weatherlogger.base.data.room.WeatherRoomDatabase
import com.anas.weatherlogger.features.details.data.local.DetailsLocalDataSource
import com.anas.weatherlogger.features.details.data.repo.DetailsRepository
import com.anas.weatherlogger.features.details.data.repo.DetailsRepositoryImpl
import com.anas.weatherlogger.features.details.domain.DetailsUseCase
import com.anas.weatherlogger.features.details.viewmodel.DetailsViewModel
import com.anas.weatherlogger.features.main.data.local.MainLocalDataSource
import com.anas.weatherlogger.features.main.data.remote.MainRemoteDataSource
import com.anas.weatherlogger.features.main.data.repo.MainRepository
import com.anas.weatherlogger.features.main.data.repo.MainRepositoryImpl
import com.anas.weatherlogger.features.main.domain.MainUseCase
import com.anas.weatherlogger.features.main.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class WeatherLoggerApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherLoggerApplication)
            modules(databaseModule, mainModule, detailsModule)
        }
    }

    private val mainModule = module {
        single { MainRemoteDataSource() }
        single { MainLocalDataSource(get()) }
        single<MainRepository> { MainRepositoryImpl(get(), get()) }
        single { MainUseCase(get()) }

        viewModel { MainViewModel(get(), get()) }

    }

    private val detailsModule = module {
        single { DetailsLocalDataSource(get()) }
        single<DetailsRepository> { DetailsRepositoryImpl(get()) }
        single { DetailsUseCase(get()) }

        viewModel { DetailsViewModel(get()) }
    }

    private val databaseModule = module {
        single { WeatherRoomDatabase.getInstance(this@WeatherLoggerApplication) }
    }
}