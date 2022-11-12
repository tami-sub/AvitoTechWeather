package com.example.avitotechweather.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.data.repository.OpenWeatherMapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val openWeatherMapRepository:
                    OpenWeatherMapRepository)
    : ViewModel() {

    private var _numberLiveData = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> = _numberLiveData

    private var _cityNameLiveData = MutableLiveData<String?>()
    val cityNameLiveData: MutableLiveData<String?> = _cityNameLiveData


    init{
        _numberLiveData.value = 20
        getCoordinates("Санкт-Петербург")
    }
    fun increaseNumber(){
        _numberLiveData.value = numberLiveData.value?.plus(2)
    }

    fun getCoordinates(cityName: String){
        viewModelScope.launch {
            val response = openWeatherMapRepository.getCityLatLon(cityName)[0]
            _cityNameLiveData.value = "${response.lat}, ${response.lon}"

            delay(2000)
//            openWeatherMapRepository.getCurrentWeather(cityName)
//
        }
    }
}