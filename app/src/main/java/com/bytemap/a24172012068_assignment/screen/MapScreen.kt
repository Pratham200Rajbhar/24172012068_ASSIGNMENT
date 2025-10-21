package com.bytemap.a24172012068_assignment.screen

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bytemap.a24172012068_assignment.compose.OpenStreetMapView
import com.bytemap.a24172012068_assignment.compose.addMarker
import com.bytemap.a24172012068_assignment.compose.animateToLocation
import com.bytemap.a24172012068_assignment.compose.clearMarkers
import com.bytemap.a24172012068_assignment.compose.LocationSearchBar
import com.bytemap.a24172012068_assignment.utils.LocationManager
import com.bytemap.a24172012068_assignment.utils.LocationResult
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen() {
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    
    var currentLocation by remember { mutableStateOf<GeoPoint?>(null) }
    var mapView by remember { mutableStateOf<MapView?>(null) }
    var isLocationEnabled by remember { mutableStateOf(false) }
    var locationError by remember { mutableStateOf<String?>(null) }
    
    val locationManager = remember { LocationManager(context) }
    
    LaunchedEffect(locationManager) {
        locationManager.currentLocation.collect { location ->
            currentLocation = location
        }
    }
    
    LaunchedEffect(locationManager) {
        locationManager.isLocationEnabled.collect { enabled ->
            isLocationEnabled = enabled
        }
    }
    
    LaunchedEffect(locationManager) {
        locationManager.locationError.collect { error ->
            locationError = error
        }
    }
    
    LaunchedEffect(locationPermissionState.status) {
        if (locationPermissionState.status.isGranted) {
            locationManager.startLocationUpdates()
        }
    }
    
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            locationPermissionState.status.isGranted -> {
                OpenStreetMapView(
                    modifier = Modifier.fillMaxSize(),
                    initialLocation = currentLocation ?: GeoPoint(37.7749, -122.4194),
                    onLocationChanged = { location ->
                        currentLocation = location
                    },
                    onMapReady = { map ->
                        mapView = map
                        currentLocation?.let { location ->
                            map.addMarker(
                                location = location,
                                title = "Your Location",
                                description = "Current position"
                            )
                        }
                    },
                    showUserLocation = true,
                    enableZoomControls = true,
                    enableMultiTouchControls = true
                )
                
                LocationSearchBar(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 16.dp),
                    onLocationSelected = { locationResult ->
                        mapView?.clearMarkers()
                        
                        mapView?.addMarker(
                            location = locationResult.geoPoint,
                            title = locationResult.name,
                            description = locationResult.address
                        )
                        
                        mapView?.animateToLocation(locationResult.geoPoint, 15.0)
                    },
                    onCurrentLocationRequested = {
                        currentLocation?.let { location ->
                            mapView?.clearMarkers()
                            mapView?.addMarker(
                                location = location,
                                title = "Your Location",
                                description = "Current position"
                            )
                            mapView?.animateToLocation(location, 15.0)
                        } ?: run {
                            locationManager.startLocationUpdates()
                        }
                    }
                )
                
                if (locationError != null) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Red.copy(alpha = 0.9f)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Warning",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = locationError ?: "Location Error",
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 2
                            )
                        }
                    }
                }
            }
            
            locationPermissionState.status.shouldShowRationale -> {
                PermissionRationaleCard(
                    onRequestPermission = { locationPermissionState.launchPermissionRequest() }
                )
            }
            
            else -> {
                PermissionRequestCard(
                    onRequestPermission = { locationPermissionState.launchPermissionRequest() }
                )
            }
        }
    }
}

@Composable
fun PermissionRequestCard(
    onRequestPermission: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Color(0xFF2196F3),
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Location Permission Required",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                text = "This app needs location permission to show your position on the map and provide location-based services.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Button(
                onClick = onRequestPermission,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text(
                    text = "Grant Permission",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun PermissionRationaleCard(
    onRequestPermission: () -> Unit
    ) {
        Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = Color(0xFFFF9800),
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 16.dp)
                )
                Text(
                text = "Location Permission Needed",
                fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Location access is required to display your current position on the map. Please grant permission to continue.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Button(
                onClick = onRequestPermission,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800)
                )
            ) {
                Text(
                    text = "Grant Permission",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    MapScreen()
}
