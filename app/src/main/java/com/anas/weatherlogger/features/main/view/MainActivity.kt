package com.anas.weatherlogger.features.main.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anas.weatherlogger.R
import com.anas.weatherlogger.features.details.view.DetailsActivity
import com.anas.weatherlogger.features.main.entities.Coordinates
import com.anas.weatherlogger.features.main.entities.WeatherEntity
import com.anas.weatherlogger.features.main.view.adapter.WeatherAdapter
import com.anas.weatherlogger.features.main.view.listeners.OnClickListener
import com.anas.weatherlogger.features.main.viewmodel.MainViewModel
import com.anas.weatherlogger.utils.PermissionsUtils
import com.anas.weatherlogger.utils.constants.Constants
import com.anas.weatherlogger.utils.extensions.startActivityWithBundle
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), OnClickListener<WeatherEntity> {

    private val weatherAdapter = WeatherAdapter(mutableListOf(), this@MainActivity)
    private var disposable: Disposable? = null
    private val mMainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
        initClearButton()
        initAdapter()
    }

    private fun initClearButton() {
        btnClearAllData.setOnClickListener {
            mMainViewModel.deleteAllWeatherData()
        }
    }

    private fun initObservers() {
        mMainViewModel.currentLocationLiveData.observe(this, locationObserver)
        mMainViewModel.weatherListLiveData.observe(this, weatherListObserver)
        mMainViewModel.deleteWeatherLiveData.observe(this, deleteWeatherObserver)
    }

    private fun getCurrentLocation() {
        mMainViewModel.getCurrentLocation()
    }

    private fun getWeatherForCurrentLocation(coordinates: Coordinates) {
        mMainViewModel.getWeatherList(coordinates)
    }

    private fun initAdapter() {
        with(rvData) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = weatherAdapter
        }
    }

    private fun updateAdapter(weatherList: List<WeatherEntity>) {
        weatherAdapter.updateAdapter(weatherList)
        // In fact, we don't need to hide the list at the beginning and then show it,
        // but i had to use it just for UI Testing to ensure that the list isDisplayed.
        rvData.visibility = VISIBLE
    }

    private fun clearAdapter() {
        weatherAdapter.clearAdapter()
    }

    private fun getDataPeriodically() {
        disposable = Observable.interval(0, 10, TimeUnit.SECONDS)
            .doOnNext { getCurrentLocation() }
            .subscribe()
    }

    private val locationObserver = Observer<Coordinates?> {
        it?.let {
            getWeatherForCurrentLocation(it)
        } ?: run {
            showErrorSnackBar()
        }
    }

    private val weatherListObserver = Observer<List<WeatherEntity>> {
        updateAdapter(it)
    }

    private val deleteWeatherObserver = Observer<Int> {
        clearAdapter()
    }

    private fun showErrorSnackBar() {
        Snackbar.make(
            constraintLayout,
            R.string.errors_no_location_data_found,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun requestLocationPermission() {
        PermissionsUtils.requestPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION,
            PermissionsUtils.PERMISSIONS_REQUEST_LOCATION
        )
    }

    override fun onClick(data: WeatherEntity) {
        val bundle = Bundle().apply {
            putLong(Constants.WEATHER_ID, data.id)
        }
        startActivityWithBundle(DetailsActivity::class.java, bundle)
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMainViewModel.currentLocationLiveData.removeObserver(locationObserver)
        mMainViewModel.weatherListLiveData.removeObserver(weatherListObserver)
        mMainViewModel.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save) {
            if (PermissionsUtils.isLocationPermissionGranted(this))
                getDataPeriodically()
            else
                requestLocationPermission()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PermissionsUtils.PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getDataPeriodically()
                }
            }
        }
    }
}
