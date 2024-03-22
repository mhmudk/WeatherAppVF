package com.example.weatherappvf.core.data_state

import com.example.weatherappvf.domain.model.forcast_weather.ForcastResponse


data class ForcastWeatherState(
    val forcastWeatherInfo: ForcastResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
