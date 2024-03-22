package com.example.weatherappvf.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappvf.core.data_state.ForcastWeatherState
import com.example.weatherappvf.domain.repository.WeatherRepository
import com.example.weatherappvf.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForcastWeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
): ViewModel() {

    var stateForcastWeather by mutableStateOf(ForcastWeatherState())

    fun fetchForcastWeather(lat:Double,long:Double) {
        viewModelScope.launch {
            stateForcastWeather = stateForcastWeather.copy(
                isLoading = true,
                error = null
            )
                when(  val result = repository.fetchWeeklyWeatherForecastRepo(long = long, lat = lat)){
                    is Resource.Success -> {
                        stateForcastWeather = stateForcastWeather.copy(
                            forcastWeatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        stateForcastWeather = stateForcastWeather.copy(
                            forcastWeatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

        }
    }

}