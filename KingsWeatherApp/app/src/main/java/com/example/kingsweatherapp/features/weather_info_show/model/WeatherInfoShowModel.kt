package com.example.kingsweatherapp.features.weather_info_show.model

import com.example.kingsweatherapp.common.RequestCompleteListener
import com.example.kingsweatherapp.features.weather_info_show.model.data_class.City
import com.example.kingsweatherapp.features.weather_info_show.model.data_class.WeatherInfoResponse

interface WeatherInfoShowModel {
    fun getCityList(callback: RequestCompleteListener<MutableList<City>>)
    fun getWeatherInfo(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>)
}