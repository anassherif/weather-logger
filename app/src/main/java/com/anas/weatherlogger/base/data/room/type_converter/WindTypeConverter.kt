package com.anas.weatherlogger.base.data.room.type_converter

import androidx.room.TypeConverter
import com.anas.weatherlogger.features.main.entities.Wind
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WindTypeConverter {
    @TypeConverter
    fun fromString(value: String?): Wind? {
        val wind = object : TypeToken<Wind?>() {}.type
        return Gson().fromJson<Wind>(value, wind)
    }

    @TypeConverter
    fun fromArrayList(main: Wind?): String? {
        return Gson().toJson(main)
    }
}