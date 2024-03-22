package com.example.weatherappvf.domain.model.weather
import android.os.Parcel
import android.os.Parcelable

data class WeatherDataResponse(
    val base: String?,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String?,
    val sys: Sys,
    val timezone: Int?,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Clouds::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readParcelable(Coord::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Main::class.java.classLoader)!!,
        parcel.readString(),
        parcel.readParcelable(Sys::class.java.classLoader)!!,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        arrayListOf<Weather>().apply {
            parcel.readList(this, Weather::class.java.classLoader)
        },
        parcel.readParcelable(Wind::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(base)
        parcel.writeParcelable(clouds, flags)
        parcel.writeInt(cod)
        parcel.writeParcelable(coord, flags)
        parcel.writeInt(dt)
        parcel.writeInt(id)
        parcel.writeParcelable(main, flags)
        parcel.writeString(name)
        parcel.writeParcelable(sys, flags)
        parcel.writeValue(timezone)
        parcel.writeInt(visibility)
        parcel.writeList(weather)
        parcel.writeParcelable(wind, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherDataResponse> {
        override fun createFromParcel(parcel: Parcel): WeatherDataResponse {
            return WeatherDataResponse(parcel)
        }

        override fun newArray(size: Int): Array<WeatherDataResponse?> {
            return arrayOfNulls(size)
        }
    }
}
