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
import com.example.avitotechweather.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), LocationListener {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentBinding

    private lateinit var locationManager: LocationManager
    private var lon: Float = 0f
    private var lat: Float = 0f
    private var checkLoadGPS: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
    private fun getCurrentLocation(lat: Float, lon: Float) {
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
                currentWeather.text = it.toString()
            }
            search.setOnClickListener {
                val city = cityName.text.toString().trim()
                viewModel.getCurrentWeather(city, true)
                viewModel.getDayWeather(city)
            }
        }
        binding.geolocation.setOnClickListener {
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
//            getCurrentLocation(lat, lon)
            checkLoadGPS = true
        }
        catch (e: Exception){
            Log.d("JOKA", e.message.toString())
        }
    }

}