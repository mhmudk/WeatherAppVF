package com.example.weatherappvf.presentation

import HourlyWeatherDisplay
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappvf.core.data_state.ForcastWeatherState

@Composable
fun WeatherForecast(
    state: ForcastWeatherState,
    modifier: Modifier = Modifier
) {
    state.forcastWeatherInfo!!.list.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Week days",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(content = {
                items(data) { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(150.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            })
        }
    }

}
