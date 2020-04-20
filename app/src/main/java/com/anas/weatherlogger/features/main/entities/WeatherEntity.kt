package com.anas.weatherlogger.features.main.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WeatherEntity() : Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var main: Main? = null
    var wind: Wind? = null
    var date: Long? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        date = parcel.readValue(Long::class.java.classLoader) as? Long
        main = parcel.readValue(Main::class.java.classLoader) as? Main
        wind = parcel.readValue(Wind::class.java.classLoader) as? Wind
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeValue(date)
        parcel.writeValue(main)
        parcel.writeValue(wind)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherEntity> {
        override fun createFromParcel(parcel: Parcel): WeatherEntity {
            return WeatherEntity(parcel)
        }

        override fun newArray(size: Int): Array<WeatherEntity?> {
            return arrayOfNulls(size)
        }
    }
}
