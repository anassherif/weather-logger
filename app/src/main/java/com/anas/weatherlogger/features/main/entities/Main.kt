package com.anas.weatherlogger.features.main.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Main(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("sea_level") val seaLevel: Double,
    @SerializedName("grnd_level") val groundLevel: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(temp)
        parcel.writeDouble(pressure)
        parcel.writeDouble(humidity)
        parcel.writeDouble(tempMin)
        parcel.writeDouble(tempMax)
        parcel.writeDouble(seaLevel)
        parcel.writeDouble(groundLevel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Main> {
        override fun createFromParcel(parcel: Parcel): Main {
            return Main(parcel)
        }

        override fun newArray(size: Int): Array<Main?> {
            return arrayOfNulls(size)
        }
    }
}