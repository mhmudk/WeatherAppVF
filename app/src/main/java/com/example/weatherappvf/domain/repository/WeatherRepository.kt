package com.example.weatherappvf.domain.repository

import com.example.weatherappvf.domain.model.forcast_weather.ForcastResponse
import com.example.weatherappvf.domain.model.weather.WeatherDataResponse
import com.example.weatherappvf.core.utils.Resource


interface WeatherRepository {
    suspend fun fetchWeatherDataByCityRepo(city:String): Resource<WeatherDataResponse>
    suspend fun fetchWeeklyWeatherForecastRepo(lat: Double, long: Double): Resource<ForcastResponse>
}