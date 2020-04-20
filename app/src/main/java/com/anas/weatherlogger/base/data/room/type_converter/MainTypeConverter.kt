package com.anas.weatherlogger.base.data.room.type_converter

import androidx.room.TypeConverter
import com.anas.weatherlogger.features.main.entities.Main
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Main? {
        val main = object : TypeToken<Main?>() {}.type
        return Gson().fromJson<Main>(value, main)
    }

    @TypeConverter
    fun fromArrayList(main: Main?): String? {
        return Gson().toJson(main)
    }
}