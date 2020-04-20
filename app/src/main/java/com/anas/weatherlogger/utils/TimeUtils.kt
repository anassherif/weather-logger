package com.anas.weatherlogger.utils

import java.text.SimpleDateFormat

class TimeUtils {

    fun getFormattedTime(timeInMillis: Long): String {
        val sdf = SimpleDateFormat.getDateTimeInstance()
        return sdf.format(timeInMillis)
    }
}