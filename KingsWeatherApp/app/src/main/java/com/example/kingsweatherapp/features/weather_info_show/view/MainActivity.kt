package com.example.kingsweatherapp.features.weather_info_show.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kingsweatherapp.features.weather_info_show.model.data_class.City
import com.example.kingsweatherapp.features.weather_info_show.model.data_class.WeatherData
import com.example.kingsweatherapp.features.weather_info_show.viewmodel.WeatherInfoViewModel
import com.example.kingsweatherapp.features.weather_info_show.viewmodel.WeatherInfoViewModelFactory
import com.example.kingsweatherapp.utils.convertToListOfCityName
import com.hellohasan.KingsWeatherApp.R
import com.hellohasan.KingsWeatherApp.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: WeatherInfoViewModelFactory

    private lateinit var viewModel: WeatherInfoViewModel

    private var cityList: MutableList<City> = mutableListOf()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize ViewModel
        viewModel = ViewModelProvider(this, factory).get(WeatherInfoViewModel::class.java)

        // set LiveData and View click listeners before the call for data fetching
        setLiveDataListeners()
        setViewClickListener()

        /**
         * Fetch city list when Activity open.
         * It's not a very good way that, passing model in every methods of ViewModel. For the sake
         * of simplicity I did so. In real production level App, we can inject out model to ViewModel
         * as a parameter by any dependency injection library like Dagger.
         */
        viewModel.getCityList()
    }

    private fun setViewClickListener() {
        // View Weather button click listener
        binding.layoutInput.apply {
            btnViewWeather.setOnClickListener {
                val selectedCityId = cityList[spinner.selectedItemPosition].id
                viewModel.getWeatherInfo(selectedCityId) // fetch weather info
            }
        }
    }

    private fun setLiveDataListeners() {

        /**
         * When ViewModel PUSH city list to LiveData then this `onChanged()`‍ method will be called.
         * Here we subscribe the LiveData of City list. We don't pull city list from ViewModel.
         * We subscribe to the data source for city list. When LiveData of city list is updated
         * inside ViewModel, below onChanged() method will triggered instantly.
         * City list is fetching from a small local JSON file. So we don't need any ProgressBar here.
         *
         * For better understanding, I didn't use lambda in this method call. Rather thant lambda I
         * implement `Observer` interface in general format. Hope you will understand the inline
         * implementation of `Observer` interface. Rest of the `observe()` method, I've used lambda
         * to short the code.
         */
        viewModel.cityListLiveData.observe(this, object : Observer<MutableList<City>> {
            override fun onChanged(cities: MutableList<City>) {
                setCityListSpinner(cities)
            }
        })

        /**
         * If ViewModel failed to fetch City list from data source, this LiveData will be triggered.
         * I know it's not good to make separate LiveData both for Success and Failure, but for sake
         * of simplification I did it. We can handle all of our errors from our Activity or Fragment
         * Base classes. Another way is: using a Generic wrapper class where you can set the success
         * or failure status for any types of data model.
         *
         * Here I've used lambda expression to implement Observer interface in second parameter.
         */
        viewModel.cityListFailureLiveData.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })

        /**
         * ProgressBar visibility will be handled by this LiveData. ViewModel decides when Activity
         * should show ProgressBar and when hide.
         *
         * Here I've used lambda expression to implement Observer interface in second parameter.
         */
        viewModel.progressBarLiveData.observe(this, Observer { isShowLoader ->
            if (isShowLoader)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        })

        /**
         * This method will be triggered when ViewModel successfully receive WeatherData from our
         * data source (I mean Model). Activity just observing (subscribing) this LiveData for showing
         * weather information on UI. ViewModel receives Weather data API response from Model via
         * Callback method of Model. Then ViewModel apply some business logic and manipulate data.
         * Finally ViewModel PUSH WeatherData to `weatherInfoLiveData`. After PUSHING into it, below
         * method triggered instantly! Then we set the data on UI.
         *
         * Here I've used lambda expression to implement Observer interface in second parameter.
         */
        viewModel.weatherInfoLiveData.observe(this, Observer { weatherData ->
            setWeatherInfo(weatherData)
        })

        /**
         * If ViewModel faces any error during Weather Info fetching API call by Model, then PUSH the
         * error message into `weatherInfoFailureLiveData`. After that, this method will be triggered.
         * Then we will hide the output view and show error message on UI.
         *
         * Here I've used lambda expression to implement Observer interface in second parameter.
         */
        viewModel.weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->
            binding.apply {
                outputGroup.visibility = View.GONE
                tvErrorMessage.visibility = View.VISIBLE
                tvErrorMessage.text = errorMessage
            }
        })
    }

    private fun setCityListSpinner(cityList: MutableList<City>) {
        this.cityList = cityList

        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            this.cityList.convertToListOfCityName()
        )

        binding.layoutInput.spinner.adapter = arrayAdapter
    }

    private fun setWeatherInfo(weatherData: WeatherData) {
        binding.apply {
            outputGroup.visibility = View.VISIBLE
            tvErrorMessage.visibility = View.GONE

            layoutWeatherBasic.apply {
                tvDateTime.text = weatherData.dateTime
                tvTemperature.text = weatherData.temperature
                tvCityCountry.text = weatherData.cityAndCountry
                Glide.with(this@MainActivity).load(weatherData.weatherConditionIconUrl).into(ivWeatherCondition)
                tvWeatherCondition.text = weatherData.weatherConditionIconDescription
            }

            layoutWeatherAdditional.apply {
                tvHumidityValue.text = weatherData.humidity
                tvPressureValue.text = weatherData.pressure
                tvVisibilityValue.text = weatherData.visibility
            }

            layoutSunsetSunrise.apply {
                tvSunriseTime.text = weatherData.sunrise
                tvSunsetTime.text = weatherData.sunset
            }

        }
    }
}
