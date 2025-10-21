package com.bytemap.a24172012068_assignment.compose

import android.content.Context
import android.location.Location
import android.util.AttributeSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@Composable
fun OpenStreetMapView(
    modifier: Modifier = Modifier,
    initialLocation: GeoPoint? = null,
    onLocationChanged: (GeoPoint) -> Unit = {},
    onMapReady: (MapView) -> Unit = {},
    showUserLocation: Boolean = true,
    enableZoomControls: Boolean = true,
    enableMultiTouchControls: Boolean = true
) {
    val context = LocalContext.current
    var mapView by remember { mutableStateOf<MapView?>(null) }
    var myLocationOverlay by remember { mutableStateOf<MyLocationNewOverlay?>(null) }

    LaunchedEffect(context) {
        Configuration.getInstance().load(
            context,
            context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        )
        Configuration.getInstance().userAgentValue = "YourApp/1.0"
    }

    AndroidView(
        factory = { ctx ->
            MapView(ctx).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(enableMultiTouchControls)
                isClickable = true
                isFocusable = true
                isFocusableInTouchMode = true
                
                setBuiltInZoomControls(enableZoomControls)
                setHorizontalMapRepetitionEnabled(false)
                setVerticalMapRepetitionEnabled(false)
                isHorizontalMapRepetitionEnabled = false
                isVerticalMapRepetitionEnabled = false
                
                initialLocation?.let { location ->
                    controller.setCenter(location)
                    controller.setZoom(15.0)
                }

                addMapListener(object : MapListener {
                    override fun onScroll(event: ScrollEvent?): Boolean {
                        event?.let {
                            val center = GeoPoint(
                                it.source.projection.currentCenter.latitude,
                                it.source.projection.currentCenter.longitude
                            )
                            onLocationChanged(center)
                        }
                        return false
                    }

                    override fun onZoom(event: ZoomEvent?): Boolean {
                        return false
                    }
                })

                mapView = this
                onMapReady(this)
            }
        },
        modifier = modifier.fillMaxSize(),
        update = { view ->
            if (showUserLocation && myLocationOverlay == null) {
                val locationOverlay = MyLocationNewOverlay(
                    GpsMyLocationProvider(context),
                    view
                )
                locationOverlay.enableMyLocation()
                view.overlays.add(locationOverlay)
                myLocationOverlay = locationOverlay
            }
        }
    )

    DisposableEffect(Unit) {
        onDispose {
            mapView?.onDetach()
        }
    }
}

fun MapView.addMarker(
    location: GeoPoint,
    title: String = "",
    description: String = "",
    iconResId: Int? = null
): Marker {
    val marker = Marker(this)
    marker.position = location
    marker.title = title
    marker.snippet = description
    iconResId?.let { marker.icon = resources.getDrawable(it, null) }
    overlays.add(marker)
    return marker
}

fun MapView.animateToLocation(location: GeoPoint, zoomLevel: Double = 15.0) {
    controller.animateTo(location)
    controller.setZoom(zoomLevel)
}

fun MapView.getCurrentCenter(): GeoPoint {
    return projection.currentCenter
}

fun MapView.clearMarkers() {
    overlays.removeAll { it is Marker }
    invalidate()
}

fun MapView.addMarkers(markers: List<Pair<GeoPoint, String>>) {
    markers.forEach { (location, title) ->
        addMarker(location, title)
    }
}

fun MapView.fitMarkersInView() {
    val markers = overlays.filterIsInstance<Marker>()
    if (markers.isNotEmpty()) {
        var minLat = Double.MAX_VALUE
        var maxLat = Double.MIN_VALUE
        var minLon = Double.MAX_VALUE
        var maxLon = Double.MIN_VALUE
        
        markers.forEach { marker ->
            val lat = marker.position.latitude
            val lon = marker.position.longitude
            minLat = minOf(minLat, lat)
            maxLat = maxOf(maxLat, lat)
            minLon = minOf(minLon, lon)
            maxLon = maxOf(maxLon, lon)
        }
        
        val boundingBox = org.osmdroid.util.BoundingBox(maxLat, maxLon, minLat, minLon)
        zoomToBoundingBox(boundingBox, true, 50)
    }
}
