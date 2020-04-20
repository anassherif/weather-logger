package com.anas.weatherlogger.base.data.room.type_converter

import androidx.room.TypeConverter
import com.anas.weatherlogger.features.main.entities.Coordinates
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CoordinatesTypeConverter {


    @TypeConverter
    fun fromString(value: String?): Coordinates? {
        val coordinates = object : TypeToken<Coordinates?>() {}.type
        return Gson().fromJson<Coordinates>(value, coordinates)
    }

    @TypeConverter
    fun fromArrayList(coordinates: Coordinates?): String? {
        return Gson().toJson(coordinates)
    }
}