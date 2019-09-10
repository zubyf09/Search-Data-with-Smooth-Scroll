package com.vivy.doctorsearch.ui.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(){

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        const val LOCATION_PERMISSION_REQUEST = 1

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutById())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            listenForLocation()
        } else {
            requestLocationPermission()
        }
        initUI()
    }

    private fun createLocationRequest(): LocationRequest? {
        return LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    @SuppressLint("MissingPermission")
    private fun listenForLocation() {
        createLocationRequest()?.let {
            val builder = LocationSettingsRequest.Builder().addLocationRequest(it)
            val client: SettingsClient = LocationServices.getSettingsClient(this)
            val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
            task.addOnSuccessListener {
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    location?.let { loc ->
                        onLocationAvailable(loc)
                    }
                }.addOnFailureListener {
                    OnFailureListener { p0 -> p0.printStackTrace() }
                }
            }
            task.addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    try {
                        exception.startResolutionForResult(this, 1)
                    } catch (sendEx: IntentSender.SendIntentException) {
                    }
                }

            }

        }
    }

    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            BaseActivity.LOCATION_PERMISSION_REQUEST
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST && permissions.size == 1
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            listenForLocation()
        }

    }

    abstract fun initUI()
    abstract fun getLayoutById(): Int
    abstract  fun onLocationAvailable(location:Location)

}