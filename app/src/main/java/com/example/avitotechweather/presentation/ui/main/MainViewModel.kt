package com.example.avitotechweather.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.data.network.Utils.getProperDateTime
import com.example.avitotechweather.data.repository.OpenWeatherMapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours

@HiltViewModel
class MainViewModel @Inject constructor(private val openWeatherMapRepository:
                    OpenWeatherMapRepository)
    : ViewModel() {

    private var _numberLiveData = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> = _numberLiveData

    private var _cityNameLiveData = MutableLiveData<String?>()
    val cityNameLiveData: MutableLiveData<String?> = _cityNameLiveData

//    private var _cityNameLiveData = MutableLiveData<String?>()
//    val cityNameLiveData: MutableLiveData<String?> = _cityNameLiveData


    init{
        _numberLiveData.value = 20
        getCoordinates("Санкт-Петербург")
    }
    fun increaseNumber(){
        _numberLiveData.value = numberLiveData.value?.plus(2)
    }

    fun getCoordinates(cityName: String){
        viewModelScope.launch {
            Log.d("JOKA", "BOKAS")

            openWeatherMapRepository.getCurrentWeather(cityName).onSuccess {
                Log.d("KOT", "NAKONEC-TO")
                try {
                    _cityNameLiveData.value = "${it.name}, ${
                        getProperDateTime(it.dataTime)
                        
                    }"
                }
                catch (e: Exception){
                    Log.d("JOKA", e.message.toString())
                }


            }.onFailure{
                Log.d("KOT", it.message.toString())
                Log.d("KOT", "BLYAAA")
            }

//            openWeatherMapRepository.getCityLatLon(cityName).onSuccess {
//                Log.d("KOT", "Initial coordgeting ok")
//
//                _cityNameLiveData.value = "${it[0].lat}, ${it[0].lon}"
//            }.onFailure {
//                Log.d("KOT", it.message.toString())
//                Log.d("KOT", it.localizedMessage.toString())
//            }

        }
    }
}