package com.example.weatherappvf.presentation.nav_controller

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController =navController , startDestination = "Home"  ){

        composable(route = "Home") {
//            CityScreen(navController = navController)
        }


    }
}