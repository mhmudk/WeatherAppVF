package com.example.weatherappvf.data.repository

import com.example.weatherappvf.data.remote.WeatherApi
import com.example.weatherappvf.domain.model.forcast_weather.ForcastResponse
import com.example.weatherappvf.domain.model.weather.WeatherDataResponse
import com.example.weatherappvf.domain.repository.WeatherRepository
import com.example.weatherappvf.core.utils.Resource

import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {


    override suspend fun fetchWeatherDataByCityRepo(city: String): Resource<WeatherDataResponse> {
        return try {
            Resource.Success(
                data = api.fetchWeatherDataByCity(
                    city = city
                )

            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun fetchWeeklyWeatherForecastRepo(
        lat: Double,
        long: Double
    ): Resource<ForcastResponse> {
        return try {
            Resource.Success(
                data = api.fetchWeeklyWeatherForecast(
                    latitude = lat,
                    longitude = long,
                )

            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}