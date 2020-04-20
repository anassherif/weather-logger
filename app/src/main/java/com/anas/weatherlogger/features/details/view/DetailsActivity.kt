package com.anas.weatherlogger.features.details.view

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.anas.weatherlogger.R
import com.anas.weatherlogger.features.details.viewmodel.DetailsViewModel
import com.anas.weatherlogger.features.main.entities.Main
import com.anas.weatherlogger.features.main.entities.WeatherEntity
import com.anas.weatherlogger.features.main.entities.Wind
import com.anas.weatherlogger.utils.TimeUtils
import com.anas.weatherlogger.utils.constants.Constants
import com.anas.weatherlogger.utils.extensions.getBundleData
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val mDetailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        getBundleData()?.getLong(Constants.WEATHER_ID)?.let {
            getWeatherDetails(it)
        }
    }

    private fun getWeatherDetails(id: Long) {
        mDetailsViewModel.weatherDataLiveData.observe(this, weatherDetailsObserver)
        mDetailsViewModel.getWeatherDetails(id)
    }

    private val weatherDetailsObserver = Observer<WeatherEntity?> {
        it?.let {
            updateWeatherData(it)
        } ?: run {
            showNoDataAvailable()
        }
    }

    private fun updateWeatherData(weatherEntity: WeatherEntity?) {
        weatherEntity?.let {
            it.main?.let { main -> updateTempViews(main) }
            it.wind?.let { wind -> updateWindView(wind) }
            it.date?.let { date -> updateDate(date) }

            cvDetails.visibility = VISIBLE
        }
    }

    private fun updateWindView(wind: Wind) {
        tvWindSpeed.text = wind.speed.toString()
    }

    private fun updateTempViews(main: Main) {
        tvTemp.text = String.format(getString(R.string.format_temp), main.temp.toString())
        tvPressure.text =
            String.format(getString(R.string.format_pressure), main.pressure.toString())
        tvHumidity.text =
            String.format(getString(R.string.format_humidity), main.humidity.toString())
    }

    private fun updateDate(date: Long) {
        tvDate.text = String.format(
            getString(R.string.format_date),
            TimeUtils().getFormattedTime(date)
        )
    }

    private fun showNoDataAvailable() {
        Snackbar.make(
            constraintLayout,
            R.string.errors_no_data_available,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDetailsViewModel.onDestroy()
    }
}
