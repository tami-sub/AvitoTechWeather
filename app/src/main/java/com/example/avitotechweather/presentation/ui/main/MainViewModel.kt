package com.example.avitotechweather.presentation.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.utils.Utils.getProperDateTime
import com.example.avitotechweather.domain.entity.Day
import com.example.avitotechweather.domain.entity.WeatherCurrentDTO
import com.example.avitotechweather.domain.interactor.IOpenWeatherMapInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val openWeatherMapInteractor:
    IOpenWeatherMapInteractor
) : ViewModel() {

    private var _cityNameLiveData = MutableLiveData<String?>()
    val cityNameLiveData: MutableLiveData<String?> = _cityNameLiveData

    private var _currentWeatherLiveData = MutableLiveData<WeatherCurrentDTO>()
    val currentWeatherLiveData: MutableLiveData<WeatherCurrentDTO> = _currentWeatherLiveData

    private var _dayWeatherLiveData = MutableLiveData<List<Day>>()
    val dayWeatherLiveData: MutableLiveData<List<Day>> = _dayWeatherLiveData

    private var _weekWeatherLiveData = MutableLiveData<List<Day>>()
    val weekWeatherLiveData: MutableLiveData<List<Day>> = _weekWeatherLiveData

    init {}

    fun getCurrentWeather(cityName: String, flag: Boolean, lat: Float = 0f, lon: Float = 0f) {
        viewModelScope.launch {

            if (flag) {
                openWeatherMapInteractor.getCurrentWeather(cityName).onSuccess {
                    putData(it)

                }.onFailure {
                    Log.d("KOT", "Current problem:  ${it.message.toString()}")
                }
            } else {
                openWeatherMapInteractor.getCurrentGeoWeather(lat.toString(), lon.toString())
                    .onSuccess {
                        putData(it)

                    }.onFailure {
                        Log.d("KOT", "Current problem:  ${it.message.toString()}")
                    }
            }

        }
    }

    fun getDayWeekWeather(city: String = "", lat: Float = 0f, lon: Float = 0f) {
        viewModelScope.launch {
            try {
                openWeatherMapInteractor.getDayWeekWeather(city, 7).onSuccess {
                    _dayWeatherLiveData.value = it.list
                }
            } catch (e: Exception) {
                Log.d("JOKA", e.message.toString())
            }
        }

        viewModelScope.launch {
            openWeatherMapInteractor.getDayWeekWeather(city, 55).onSuccess {
                _weekWeatherLiveData.value = it.list
            }
        }
    }

    fun getGeoDayWeekWeather(lat: Float, lon: Float) {
        viewModelScope.launch {
            try {
                openWeatherMapInteractor.getGeoDayWeekWeather(lat.toString(), lon.toString(),7).onSuccess {
                    _dayWeatherLiveData.value = it.list
                }
            } catch (e: Exception) {
                Log.d("JOKA", e.message.toString())
            }
        }

        viewModelScope.launch {
            openWeatherMapInteractor.getGeoDayWeekWeather(lat.toString(), lon.toString(),55).onSuccess {
                _weekWeatherLiveData.value = it.list
            }
        }
    }

    private fun putData(it: WeatherCurrentDTO) {
        try {
            _cityNameLiveData.value = "${it.name}, ${getProperDateTime(it.dataTime)}"
            _currentWeatherLiveData.value = it
        } catch (e: Exception) {
            Log.d("JOKA", e.message.toString())
        }
    }
}