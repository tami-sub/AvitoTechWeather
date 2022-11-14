package com.example.avitotechweather.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.utils.Utils.getProperDateTime
import com.example.avitotechweather.data.repository.OpenWeatherMapRepository
import com.example.avitotechweather.domain.entity.WeatherDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.stream.IntStream.range
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

    fun getCurrentWeather(cityName: String, flag: Boolean, lat: Float = 0f, lon: Float = 0f) {
        viewModelScope.launch {

            if (flag) {
                openWeatherMapRepository.getCurrentWeather(cityName).onSuccess {
                    Log.d("KOT", "NAKONEC-TO")
                    putData(it)

                }.onFailure {
                    Log.d("KOT", "Current problem:  ${it.message.toString()}")
                }
            } else {
                Log.d("Joka", " hahahahhah ${lat}")
                Log.d("Joka", " hahahahhah ${lon}")
                openWeatherMapRepository.getCurrentGeoWeather(lat.toString(), lon.toString())
                    .onSuccess {
                        Log.d("KOT", "GEO 2")
                        putData(it)

                    }.onFailure {
                        Log.d("KOT", "Current problem:  ${it.message.toString()}")
                    }
            }

        }
    }

    fun getDayWeather(city: String) {
        viewModelScope.launch {
            try {
                openWeatherMapRepository.getDayWeather(city, 7).onSuccess {
                    for (i in range(0, it.list.size)) {
                        Log.d("JOKA", "" +
                                "Город: ${it.city.name}\n" +
                                "Дата: ${it.list[i].dtTxt}\n" +
                                "Ощущается как: ${it.list[i].main.feelsLike}\n" +
                                "Температура: ${it.list[i].main.temp}\n" +
                                "Описание: ${it.list[i].weather[0].description}\n" +
                                "Скорость ветра: ${it.list[i].wind.speed}\n" +
                                "Влажность: ${it.list[i].main.humidity}\n" +
                                "Иконка: ${it.list[i].weather[0].icon}\n"
                                                    )
                    }
                }
            } catch (e: Exception) {
                Log.d("JOKA", e.message.toString())
            }
        }

        viewModelScope.launch {
            openWeatherMapRepository.getDayWeather(city, 55).onSuccess {
                for (i in range(0, it.list.size)) {
                        Log.d("BOKA", "" +
                                "Город: ${it.city.name}\n" +
                                "Дата: ${it.list[i].dtTxt}\n" +
                                "Ощущается как: ${it.list[i].main.feelsLike}\n" +
                                "Температура: ${it.list[i].main.temp}\n" +
                                "Описание: ${it.list[i].weather[0].description}\n" +
                                "Скорость ветра: ${it.list[i].wind.speed}\n" +
                                "Влажность: ${it.list[i].main.humidity}\n" +
                                "Иконка: ${it.list[i].weather[0].icon}\n"
                                                    )
                    }
            }
        }
    }

    private fun putData(it: WeatherDTO) {
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
    }
}