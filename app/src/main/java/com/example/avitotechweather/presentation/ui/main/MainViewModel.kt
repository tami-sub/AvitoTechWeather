package com.example.avitotechweather.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.data.repository.IOpenWeatherMapRepository
import com.example.avitotechweather.domain.entity.CityDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val openWeatherMapInteractor: IOpenWeatherMapRepository)
    : ViewModel() {

    private var _numberLiveData = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> = _numberLiveData

    private var _cityNameLiveData = MutableLiveData<String?>()
    val cityNameLiveData: MutableLiveData<String?> = _cityNameLiveData


    init{
        _numberLiveData.value = 20
        getCoordinates("Москва")
    }
    fun increaseNumber(){
        _numberLiveData.value = numberLiveData.value?.plus(2)
    }

    fun getCoordinates(cityName: String){
        viewModelScope.launch {
            openWeatherMapInteractor.getCityLatLon(cityName).enqueue(object : Callback<CityDTO>{
                override fun onResponse(call: Call<CityDTO>, response: Response<CityDTO>) {
                    _cityNameLiveData.value = "${response.body()?.lat}, ${response.body()?.lon}"

                    Log.d("KOT", _cityNameLiveData.toString())
                }

                override fun onFailure(call: Call<CityDTO>, t: Throwable) {
                    _cityNameLiveData.value = null
                }

            })
        }
    }
}