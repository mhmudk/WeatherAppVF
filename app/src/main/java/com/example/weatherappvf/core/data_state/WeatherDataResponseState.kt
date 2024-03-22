package com.example.weatherappvf.core.data_state

import com.example.weatherappvf.domain.model.weather.WeatherDataResponse

data class WeatherDataResponseState(
    val weatherDataInfo: WeatherDataResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
