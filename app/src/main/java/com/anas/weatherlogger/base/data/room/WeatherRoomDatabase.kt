package com.anas.weatherlogger.base.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anas.weatherlogger.base.data.room.type_converter.CoordinatesTypeConverter
import com.anas.weatherlogger.base.data.room.type_converter.MainTypeConverter
import com.anas.weatherlogger.base.data.room.type_converter.WindTypeConverter
import com.anas.weatherlogger.features.main.data.local.WeatherDAO
import com.anas.weatherlogger.features.main.entities.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    MainTypeConverter::class,
    CoordinatesTypeConverter::class,
    WindTypeConverter::class
)
abstract class WeatherRoomDatabase : RoomDatabase() {

    companion object {
        private var weatherRoomDatabase: WeatherRoomDatabase? = null
        fun getInstance(applicationContext: Context): WeatherRoomDatabase {
            if (weatherRoomDatabase == null) {
                weatherRoomDatabase = Room.databaseBuilder(
                    applicationContext, WeatherRoomDatabase::class.java,
                    "WeatherLogger"
                ).build()
            }
            return weatherRoomDatabase as WeatherRoomDatabase
        }
    }

    abstract fun weatherDAO(): WeatherDAO
}
