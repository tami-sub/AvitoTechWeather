package com.example.avitotechweather.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.utils.Utils.getProperDateTime
import com.example.avitotechweather.data.repository.OpenWeatherMapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val openWeatherMapRepository:
    OpenWeatherMapRepository,
) : ViewModel() {

    private var _cityNameLiveData = MutableLiveData<String?>()
    val cityNameLiveData: MutableLiveData<String?> = _cityNameLiveData

    private var _currentWeatherLiveData = MutableLiveData<String?>()
    val currentWeatherLiveData: MutableLiveData<String?> = _currentWeatherLiveData

    init {}

    fun getCurrentWeather(cityName: String) {
        viewModelScope.launch {

            openWeatherMapRepository.getCurrentWeather(cityName).onSuccess {
                Log.d("KOT", "NAKONEC-TO")
                try {
                    _cityNameLiveData.value = "${it.name}, ${getProperDateTime(it.dataTime)}"
                    _currentWeatherLiveData.value = "${it.main.temp}\n" +
                            "${it.main.feelsLike}\n" +
                            "${it.weather[0].description}\n" +
                            "${it.weather[0].main}\n" +
                            "${it.weather[0].icon}\n" +
                            "${it.wind.speed}\n" +
                            "${it.wind.deg}\n"
                } catch (e: Exception) {
                    Log.d("JOKA", e.message.toString())
                }

            }.onFailure {
                Log.d("KOT", "Current problem:  ${it.message.toString()}")
            }

        }
    }
}