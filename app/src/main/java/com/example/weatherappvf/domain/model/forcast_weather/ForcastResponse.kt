package com.example.weatherappvf.domain.model.forcast_weather
data class ForcastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForcastWeather>,
    val message: Int
)