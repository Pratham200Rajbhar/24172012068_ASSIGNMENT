package com.bytemap.a24172012068_assignment.utils

import android.content.Context
import android.location.Geocoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint
import java.io.IOException
import java.util.*

data class LocationResult(
    val name: String,
    val address: String,
    val geoPoint: GeoPoint
)

class GeocodingService(private val context: Context) {
    
    private val geocoder = Geocoder(context, Locale.getDefault())
    
    suspend fun searchLocation(query: String): List<LocationResult> = withContext(Dispatchers.IO) {
        try {
            if (!Geocoder.isPresent()) {
                return@withContext emptyList()
            }
            
            val results = mutableListOf<LocationResult>()
            
            // Search for locations with more results for better suggestions
            val addresses = geocoder.getFromLocationName(query, 8)
            
            addresses?.forEach { address ->
                val geoPoint = GeoPoint(
                    address.latitude,
                    address.longitude
                )
                
                // Better location name extraction
                val locationName = when {
                    address.featureName != null -> address.featureName
                    address.locality != null -> address.locality
                    address.subLocality != null -> address.subLocality
                    address.adminArea != null -> address.adminArea
                    else -> "Unknown Location"
                }
                
                // Enhanced address formatting
                val fullAddress = buildString {
                    val parts = mutableListOf<String>()
                    
                    address.subLocality?.let { parts.add(it) }
                    address.locality?.let { parts.add(it) }
                    address.adminArea?.let { parts.add(it) }
                    address.countryName?.let { parts.add(it) }
                    
                    // Remove duplicates and join
                    parts.distinct().joinToString(", ")
                }
                
                results.add(
                    LocationResult(
                        name = locationName,
                        address = fullAddress,
                        geoPoint = geoPoint
                    )
                )
            }
            
            // Remove duplicates based on coordinates
            results.distinctBy { "${it.geoPoint.latitude},${it.geoPoint.longitude}" }
        } catch (e: IOException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getAddressFromLocation(geoPoint: GeoPoint): String? = withContext(Dispatchers.IO) {
        try {
            if (!Geocoder.isPresent()) {
                return@withContext null
            }
            
            val addresses = geocoder.getFromLocation(geoPoint.latitude, geoPoint.longitude, 1)
            addresses?.firstOrNull()?.getAddressLine(0)
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getLocationName(geoPoint: GeoPoint): String? = withContext(Dispatchers.IO) {
        try {
            if (!Geocoder.isPresent()) {
                return@withContext null
            }
            
            val addresses = geocoder.getFromLocation(geoPoint.latitude, geoPoint.longitude, 1)
            addresses?.firstOrNull()?.featureName
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }
    }
}
