package com.example.weatherappvf.data.remote

import com.example.weatherappvf.domain.model.forcast_weather.ForcastResponse
import com.example.weatherappvf.domain.model.weather.WeatherDataResponse
import com.example.weatherappvf.core.utils.Utils.APP_ID
import com.example.weatherappvf.core.utils.Utils.NUMBER_OF_DAYS
import com.example.weatherappvf.core.utils.Utils.UNITS_API
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun fetchWeatherDataByCity(
        @Query("q") city: String,
        @Query("units") units: String = UNITS_API,
        @Query("appid") appId: String = APP_ID
    ): WeatherDataResponse

    @GET("forecast")
    suspend fun fetchWeeklyWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int = NUMBER_OF_DAYS,
        @Query("appid") apiKey: String = APP_ID
    ): ForcastResponse
}