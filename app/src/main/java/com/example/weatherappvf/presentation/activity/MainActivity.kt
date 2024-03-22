package com.example.weatherappvf.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherappvf.domain.model.weather.WeatherDataResponse
import com.example.weatherappvf.presentation.features.WeatherCard
import com.example.weatherappvf.presentation.WeatherForecast
import com.example.weatherappvf.presentation.theme.DarkBlue
import com.example.weatherappvf.presentation.theme.DeepBlue
import com.example.weatherappvf.presentation.theme.WeatherAppTheme
import com.example.weatherappvf.presentation.ForcastWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ForcastWeatherViewModel by viewModels()
    lateinit var weatherDataResponse: WeatherDataResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    fetchWeatherDataFromIntent()
    setContent {
            WeatherAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkBlue)
                    ) {
                        if(weatherDataResponse!=null){
                            WeatherCard(
                            state = weatherDataResponse!!,
                            backgroundColor = DeepBlue

                        )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        if(viewModel.stateForcastWeather.forcastWeatherInfo!=null) {
                            WeatherForecast(state = viewModel.stateForcastWeather)
                        }
                    }
                    if(viewModel.stateForcastWeather.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
    private  fun fetchWeatherDataFromIntent(){
        val bundle = intent.extras
        if (bundle != null) {
            weatherDataResponse = bundle.getParcelable("weatherDataResponse")!!
            viewModel.fetchForcastWeather(weatherDataResponse.coord.lat,weatherDataResponse.coord.lon)
        }
    }
}