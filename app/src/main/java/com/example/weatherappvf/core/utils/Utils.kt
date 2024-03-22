package com.example.weatherappvf.core.utils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {

    const val SAVE_CITY="saveCity"
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val APP_ID = "16d467ee63d64ea9463752f00f7ae18e"
    const val UNITS_API = "metric"
    const val NUMBER_OF_DAYS=7

    fun formatDate(dtTxt: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateTime = LocalDateTime.parse(dtTxt, inputFormatter)
        return dateTime.format(outputFormatter)
    }
}