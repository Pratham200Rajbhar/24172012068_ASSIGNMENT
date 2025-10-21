package com.bytemap.a24172012068_assignment.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import org.osmdroid.util.GeoPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class LocationManager(private val context: Context) : LocationListener {
    
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val _currentLocation = MutableStateFlow<GeoPoint?>(null)
    val currentLocation: StateFlow<GeoPoint?> = _currentLocation.asStateFlow()
    
    private val _isLocationEnabled = MutableStateFlow(false)
    val isLocationEnabled: StateFlow<Boolean> = _isLocationEnabled.asStateFlow()
    
    private val _locationError = MutableStateFlow<String?>(null)
    val locationError: StateFlow<String?> = _locationError.asStateFlow()
    
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    
    fun startLocationUpdates() {
        if (hasLocationPermission()) {
            try {
                val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                
                if (isGpsEnabled || isNetworkEnabled) {
                    _isLocationEnabled.value = true
                    _locationError.value = null
                    
                    val lastKnownLocation = getLastKnownLocation()
                    lastKnownLocation?.let { location ->
                        _currentLocation.value = GeoPoint(location.latitude, location.longitude)
                    }
                    
                    if (isGpsEnabled) {
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            5000L,
                            10f,
                            this
                        )
                    }
                    
                    if (isNetworkEnabled) {
                        locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            5000L,
                            10f,
                            this
                        )
                    }
                    
                    coroutineScope.launch {
                        delay(10000)
                        if (_currentLocation.value == null) {
                            _locationError.value = "Location not available. Please check GPS settings."
                        }
                    }
                } else {
                    _isLocationEnabled.value = false
                    _locationError.value = "Location services are disabled. Please enable GPS or network location."
                }
            } catch (e: SecurityException) {
                _isLocationEnabled.value = false
                _locationError.value = "Location permission denied."
            }
        } else {
            _isLocationEnabled.value = false
            _locationError.value = "Location permission not granted."
        }
    }
    
    fun stopLocationUpdates() {
        locationManager.removeUpdates(this)
        _isLocationEnabled.value = false
    }
    
    private fun getLastKnownLocation(): Location? {
        return try {
            if (hasLocationPermission()) {
                val gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                
                when {
                    gpsLocation != null && networkLocation != null -> {
                        if (gpsLocation.time > networkLocation.time) gpsLocation else networkLocation
                    }
                    gpsLocation != null -> gpsLocation
                    networkLocation != null -> networkLocation
                    else -> null
                }
            } else {
                null
            }
        } catch (e: SecurityException) {
            null
        }
    }
    
    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED || 
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    override fun onLocationChanged(location: Location) {
        _currentLocation.value = GeoPoint(location.latitude, location.longitude)
    }
    
    override fun onProviderEnabled(provider: String) {
    }
    
    override fun onProviderDisabled(provider: String) {
    }
}
