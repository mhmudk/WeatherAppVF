package com.example.weatherappvf.presentation.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherappvf.domain.model.weather.WeatherDataResponse
import com.example.weatherappvf.presentation.features.ShowDialogError
import com.example.weatherappvf.presentation.FetchWeatherByCityViewModel
import com.example.weatherappvf.core.utils.Resource


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityActivity : ComponentActivity() {

    private val viewModel: FetchWeatherByCityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CityScreen(viewModel = viewModel)
            LaunchedEffect(viewModel.locationCity) {
                viewModel.locationCity.observe(this@CityActivity) { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            navigateToWeatherPage(resource.data!!)
                        }
                        is Resource.Error -> {
                            Log.d("Error","CityActivity --> "+resource.message)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToWeatherPage(weatherDataResponse: WeatherDataResponse) {

        val intent = Intent(this@CityActivity, MainActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("weatherDataResponse",weatherDataResponse)
        intent.putExtras(bundle)
        try {
            startActivity(intent)
            finish()
        } catch (e: ActivityNotFoundException) {
            Log.d("Error","CityActivity --> Weather page not found")

        } catch (e: Exception) {
            Log.d("Error","CityActivity -->" + e.message) }

    }


    @Composable
    fun CityScreen( viewModel: FetchWeatherByCityViewModel) {
        val state by viewModel.state.collectAsState()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.cityName,
                onValueChange = viewModel::onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                label = { Text("Enter the city", textAlign = TextAlign.Start) },
                isError = state.isError,
                )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onSearchClick(state.cityName)
                          },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Search")
            }
        }

    }

}
