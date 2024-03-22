package com.example.weatherappvf.presentation.activity

data class CityNameUiState(
    val isLoading: Boolean = true,
    val isError: Boolean =false,
    val errMsg: String ="",
    val cityName: String = "",
)
