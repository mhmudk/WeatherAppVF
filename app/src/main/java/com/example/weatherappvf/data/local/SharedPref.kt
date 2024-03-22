package com.example.weatherappvf.data.local

import android.content.Context
import android.preference.PreferenceManager
import com.example.weatherappvf.core.utils.Utils.SAVE_CITY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context : Context){
    val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)

    fun getCityFromPref(): String {
        return sharedPreference.getString(SAVE_CITY, "")!!
    }
    fun saveCityToPref(query: String) {
        sharedPreference.edit().putString(SAVE_CITY, query).apply()
    }
}