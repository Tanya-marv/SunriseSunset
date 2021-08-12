package com.example.sunrisesunset.presentation

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.sunrisesunset.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel> ()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
        findViewById<ImageButton>(R.id.ib_gps).setOnClickListener {
            getPermission()
            Toast.makeText(this, "All times are in UTC!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPermission() {
        if (ActivityCompat.checkSelfPermission(this@MainActivity, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation()
        } else {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(ACCESS_FINE_LOCATION), REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.count() > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getCurrentLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            viewModel.retrieveSun(location.latitude, location.longitude)
            setContent(location)
            observeLiveData()
        }
    }

    private fun setContent(location: Location) {
        findViewById<TextView>(R.id.tv_lat_info).text = location.latitude.toString()
        findViewById<TextView>(R.id.tv_lon_info).text = location.longitude.toString()
    }

    private fun observeLiveData() {
        viewModel.dayInfoLiveData.observe(this) { dayInfo ->
            if (dayInfo != null) {
                findViewById<TextView>(R.id.tv_sunrise).text = dayInfo.results.sunrise
                findViewById<TextView>(R.id.tv_sunset).text = dayInfo.results.sunset
                findViewById<TextView>(R.id.tv_solar_noon).text = dayInfo.results.solarNoon
                findViewById<TextView>(R.id.tv_day_length).text = dayInfo.results.dayLength
                findViewById<TextView>(R.id.tv_civil_tb).text = dayInfo.results.civilTwilightBegin
                findViewById<TextView>(R.id.tv_civil_te).text = dayInfo.results.civilTwilightEnd
                findViewById<TextView>(R.id.tv_nautical_tb).text = dayInfo.results.nauticalTwilightBegin
                findViewById<TextView>(R.id.tv_nautical_te).text = dayInfo.results.nauticalTwilightEnd
                findViewById<TextView>(R.id.tv_astronomical_tb).text = dayInfo.results.astronomicalTwilightBegin
                findViewById<TextView>(R.id.tv_astronomical_te).text = dayInfo.results.astronomicalTwilightEnd
            }
        }
        viewModel.errorLiveData.observe(this) { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onStop() {
        super.onStop()
        viewModel.cancel()
    }

    companion object {
        const val REQUEST_CODE = 105
    }
}