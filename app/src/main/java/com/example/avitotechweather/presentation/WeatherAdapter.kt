package com.example.avitotechweather.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.avitotechweather.databinding.ItemWeatherBinding
import com.example.avitotechweather.domain.entity.WeatherAllDTO
import com.example.avitotechweather.utils.Utils.getIcon

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var weathers = mutableListOf<WeatherAllDTO.Days>()

    class WeatherViewHolder(private val binding: ItemWeatherBinding)
        : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(weather: WeatherAllDTO.Days) {
            binding.also { x ->
                x.dateTime.text = "дата: ${weather.dtTxt}"
                x.humidity.text = "влажность ${weather.main.humidity}%"
                x.feelsLike.text = "ощущается как ${weather.main.feelsLike}°C"
                x.pressure.text = "давление ${weather.main.pressure}"
                x.textTemperature.text = "${weather.main.temp}°C"
                x.weatherDescription.text = weather.weather[0].description
                x.windSpeed.text = "скорость ветра: ${weather.wind.speed}м/c"
                Glide.with(x.root)
                    .load(getIcon(weather.weather[0].icon))
                    .into(x.iconWeather)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        weathers.getOrNull(position)?.let { weather ->
            holder.bind(weather)
        }
    }

    fun setItems(weatherList: List<WeatherAllDTO.Days>) {
        weathers = weatherList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = weathers.size
}