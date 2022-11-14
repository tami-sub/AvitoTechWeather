package com.example.avitotechweather.presentation.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.avitotechweather.databinding.MainFragmentBinding
import com.example.avitotechweather.domain.entity.WeatherAllDTO
import com.example.avitotechweather.presentation.WeatherAdapter
import com.example.avitotechweather.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), LocationListener {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentBinding

    private lateinit var locationManager: LocationManager
    private var lon: Float = 0f
    private var lat: Float = 0f
    private var checkLoadGPS: Boolean = false

    private lateinit var dayAdapter: WeatherAdapter
    private lateinit var weekAdapter: WeatherAdapter
    private var flag: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        dayAdapter = WeatherAdapter()
        weekAdapter = WeatherAdapter()
        binding.recyclerViewDay.adapter = dayAdapter
        binding.recyclerViewWeek.adapter = weekAdapter
        return binding.root
    }
    private fun getCurrentLocation(lat: Float, lon: Float) {
        viewModel.getGeoDayWeekWeather(lat, lon)
        viewModel.getCurrentWeather("", false, lat, lon)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            ActivityCompat.requestPermissions(requireActivity(), permissions, 0)
        }
        with(binding) {
            viewModel.cityNameLiveData.observe(viewLifecycleOwner) {
                coordinates.text = it.toString()
            }
            viewModel.currentWeatherLiveData.observe(viewLifecycleOwner) {
                itemWeather.feelsLike.text = it.main.feelsLike.toString()
                itemWeather.dateTime.text = "Погода прямо сейчас"
                itemWeather.windSpeed.text = it.wind.speed.toString()
                itemWeather.humidity.text = it.main.humidity.toString()
                itemWeather.textTemperature.text = it.main.temp.toString()
                itemWeather.pressure.text = it.main.pressure.toString()
                itemWeather.weatherDescription.text = it.weather[0].description
                Glide.with(itemWeather.root)
                    .load(Utils.getIcon(it.weather[0].icon))
                    .into(itemWeather.iconWeather)
            }
            search.setOnClickListener {
                flag = true
                val city = cityName.text.toString().trim()
                viewModel.getCurrentWeather(city, true)
                viewModel.getDayWeekWeather(city)
            }
            viewModel.dayWeatherLiveData.observe(viewLifecycleOwner){
                dayAdapter.setItems(it)
            }
            viewModel.weekWeatherLiveData.observe(viewLifecycleOwner){
                weekAdapter.setItems(it)
            }
            recyclerViewDay.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerViewWeek.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }
        binding.geolocation.setOnClickListener {
            flag = false
            locationManager =
                requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5f, this)
            Log.d("JOKA", "blaya1, ${checkLoadGPS}, $lat")

            if (checkLoadGPS) {
                Log.d("JOKA", "blaya2, ${lat}")
                getCurrentLocation(lat, lon)
            }
        }
    }


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onLocationChanged(location: Location) {
        try{
            lat = location.latitude.toFloat()
            lon = location.longitude.toFloat()
            Log.d("JOKA", lat.toString())
            if(!flag) getCurrentLocation(lat, lon)
            checkLoadGPS = true
        }
        catch (e: Exception){
            Log.d("JOKA", e.message.toString())
        }
    }

}