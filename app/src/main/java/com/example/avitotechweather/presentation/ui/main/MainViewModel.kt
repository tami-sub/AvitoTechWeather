package com.example.avitotechweather.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _numberLiveData = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> = _numberLiveData
    init{
        _numberLiveData.value = 20
    }
    fun increaseNumber(){
        _numberLiveData.value = numberLiveData.value?.plus(2)
    }
}