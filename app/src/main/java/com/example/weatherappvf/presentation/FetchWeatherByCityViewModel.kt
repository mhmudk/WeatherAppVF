package com.example.weatherappvf.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappvf.core.data_state.WeatherDataResponseState
import com.example.weatherappvf.data.local.SharedPref
import com.example.weatherappvf.data.repository.WeatherRepositoryImpl
import com.example.weatherappvf.domain.model.weather.WeatherDataResponse
import com.example.weatherappvf.presentation.activity.CityNameUiState
import com.example.weatherappvf.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FetchWeatherByCityViewModel @Inject constructor(
    private val repository: WeatherRepositoryImpl,
    private val mySharedPreference: SharedPref
) : ViewModel() {

    private val _state = MutableStateFlow(CityNameUiState())
    val state =_state.asStateFlow()

    private val _locationCity = MutableLiveData<Resource<WeatherDataResponse>>()
    val locationCity: LiveData<Resource<WeatherDataResponse>> get() = _locationCity

    var weatherDataResponseState by mutableStateOf(WeatherDataResponseState())

    init {
        viewModelScope.launch {
            val lastCity = getStoredCityFromSharedPref()
            _state.update {
                it.copy(
                    isLoading = false,
                    cityName = if (lastCity != "") lastCity else "",
                    isError = false,
                    errMsg = ""
                )
            }
        }
    }

    fun getLocationFromCity(city: String) {
        viewModelScope.launch {
            val result = repository.fetchWeatherDataByCityRepo(city)
            if (result is Resource.Success) {
                weatherDataResponseState = weatherDataResponseState.copy(
                    weatherDataInfo = result.data,
                    isLoading = false,
                    error = result.message
                )
                _locationCity.value = result
            }
        }
    }

    fun saveCityToSharedPref(city: String) {
        mySharedPreference.saveCityToPref(city)
    }

    fun getStoredCityFromSharedPref(): String {
        return mySharedPreference.getCityFromPref()
    }

    fun hasStoredCityInSharedPref(): Boolean {
        val storedCity = mySharedPreference.getCityFromPref()
        return storedCity.isNotEmpty()
    }

    fun onSearchClick(city: String){
        if (!_state.value.isError){
            saveCityToSharedPref(city = city)
            getLocationFromCity(city)
        }
    }

    fun onValueChange(value: String){
        if (isNameValid(value)){
            _state.update {
                it.copy(
                    errMsg = "",
                    isLoading = false,
                    isError = false,
                    cityName = value,
                )
            }
        }else{
            _state.update {
                it.copy(
                    errMsg = "Data is empty or null",
                    isLoading = false,
                    isError = true,
                    cityName = "",
                )
            }
        }
    }

    private fun isNameValid(value: String): Boolean {
        return value.isNotEmpty() && value.isNotBlank()
    }
}