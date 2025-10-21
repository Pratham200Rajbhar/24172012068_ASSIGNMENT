package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

data class Trip(
    val id: String,
    val destination: String,
    val startTime: String,
    val endTime: String?,
    val status: TripStatus,
    val distance: String = "0.0 km"
)

enum class TripStatus {
    ACTIVE, COMPLETED, CANCELLED
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripMonitorScreen(navController: NavController?) {
    var selectedTab by remember { mutableStateOf(0) }
    var destination by remember { mutableStateOf("") }
    var showAddTripDialog by remember { mutableStateOf(false) }
    var trips by remember { mutableStateOf(listOf<Trip>()) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = Color(0xFF039be5),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = Color(0xFF039be5)
                )
            }
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = {
                    Text(
                        "Our Trip",
                        color = if (selectedTab == 0) Color(0xFF039be5) else Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = {
                    Text(
                        "Activity",
                        color = if (selectedTab == 1) Color(0xFF039be5) else Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
        
        when (selectedTab) {
            0 -> OurTripTab(
                destination = destination,
                onDestinationChange = { destination = it },
                onAddTrip = { showAddTripDialog = true },
                trips = trips,
                onTripsChange = { trips = it }
            )
            1 -> ActivityTab(trips = trips)
        }
    }
    
    if (showAddTripDialog) {
        AddTripDialog(
            destination = destination,
            onDestinationChange = { destination = it },
            onDismiss = { showAddTripDialog = false },
            onAddTrip = { newTrip ->
                trips = trips + newTrip
                showAddTripDialog = false
                destination = ""
            }
        )
    }
}

@Composable
fun OurTripTab(
    destination: String,
    onDestinationChange: (String) -> Unit,
    onAddTrip: () -> Unit,
    trips: List<Trip>,
    onTripsChange: (List<Trip>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CurrentLocationCard()
        
        SetDestinationCard(
            destination = destination,
            onDestinationChange = onDestinationChange,
            onAddTrip = onAddTrip
        )
        
        if (trips.isNotEmpty()) {
            ActiveTripCard(
                trips = trips,
                onTripsChange = onTripsChange
            )
        }
        
        TripStatusCard(trips = trips)
    }
}

@Composable
fun ActivityTab(trips: List<Trip>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (trips.isEmpty()) {
            EmptyActivityState()
        } else {
            Text(
                text = "Trip History",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C3E50),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(trips.reversed()) { trip ->
                    TripHistoryCard(trip = trip)
                }
            }
        }
    }
}

@Composable
fun EmptyActivityState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.History,
            contentDescription = "No trips",
            tint = Color(0xFFB0BEC5),
            modifier = Modifier.size(80.dp)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "No trips yet",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF546E7A),
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Start your first trip to see activity here",
            fontSize = 16.sp,
            color = Color(0xFF78909C),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CurrentLocationCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color(0xFF039be5),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Current Location", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            }
            Spacer(Modifier.height(8.dp))
            Text("Address: Shanku's Water Park, SH41", color = Color.Gray)
            Text("Coordinates: 23.474803, 72.391667", color = Color.Gray)
            Text("Speed: 0.0 km/h", color = Color.Gray)
            Text("Updates: 1 auto-updates completed", color = Color.Gray)
        }
    }
}

@Composable
fun SetDestinationCard(
    destination: String,
    onDestinationChange: (String) -> Unit,
    onAddTrip: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF039be5), modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(8.dp))
                Text("Set Destination", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            }
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = destination,
                onValueChange = onDestinationChange,
                placeholder = { Text("Enter destination name", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Default.Place, contentDescription = "Destination", tint = Color.Gray)
                }
            )
            Spacer(Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF039be5)),
                onClick = onAddTrip,
                enabled = destination.isNotBlank()
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Trip", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ActiveTripCard(
    trips: List<Trip>,
    onTripsChange: (List<Trip>) -> Unit
) {
    val activeTrip = trips.find { it.status == TripStatus.ACTIVE }
    
    if (activeTrip != null) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.DirectionsCar,
                        contentDescription = null,
                        tint = Color(0xFF43A047),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Active Trip", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = Color(0xFF2E7D32))
                }
                Spacer(Modifier.height(8.dp))
                Text("Destination: ${activeTrip.destination}", color = Color(0xFF2E7D32))
                Text("Started: ${activeTrip.startTime}", color = Color(0xFF2E7D32))
                Text("Distance: ${activeTrip.distance}", color = Color(0xFF2E7D32))
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                        onClick = {
                            val updatedTrips = trips.map { trip ->
                                if (trip.id == activeTrip.id) {
                                    trip.copy(status = TripStatus.COMPLETED, endTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()))
                                } else trip
                            }
                            onTripsChange(updatedTrips)
                        }
                    ) {
                        Text("Complete", color = Color.White, fontSize = 14.sp)
                    }
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFD32F2F)),
                        onClick = {
                            val updatedTrips = trips.map { trip ->
                                if (trip.id == activeTrip.id) {
                                    trip.copy(status = TripStatus.CANCELLED, endTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()))
                                } else trip
                            }
                            onTripsChange(updatedTrips)
                        }
                    ) {
                        Text("Cancel", color = Color(0xFFD32F2F), fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun TripStatusCard(trips: List<Trip>) {
    val hasActiveTrip = trips.any { it.status == TripStatus.ACTIVE }
    
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.PauseCircle,
                    contentDescription = null,
                    tint = if (hasActiveTrip) Color(0xFF43A047) else Color(0xFF616161),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Trip Status", fontWeight = FontWeight.Bold, fontSize = 17.sp)
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = if (hasActiveTrip) "Monitoring Active" else "Monitoring Inactive",
                color = if (hasActiveTrip) Color(0xFF43A047) else Color.Gray
            )
            Spacer(Modifier.height(8.dp))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = { },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF039be5))
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, tint = Color(0xFF039be5))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Refresh Now", color = Color(0xFF039be5), fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun TripHistoryCard(trip: Trip) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = trip.destination,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = when (trip.status) {
                            TripStatus.COMPLETED -> Color(0xFFE8F5E8)
                            TripStatus.CANCELLED -> Color(0xFFFFEBEE)
                            TripStatus.ACTIVE -> Color(0xFFE3F2FD)
                        }
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = trip.status.name,
                        fontSize = 12.sp,
                        color = when (trip.status) {
                            TripStatus.COMPLETED -> Color(0xFF2E7D32)
                            TripStatus.CANCELLED -> Color(0xFFD32F2F)
                            TripStatus.ACTIVE -> Color(0xFF1976D2)
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Started: ${trip.startTime}", fontSize = 14.sp, color = Color.Gray)
                if (trip.endTime != null) {
                    Text("Ended: ${trip.endTime}", fontSize = 14.sp, color = Color.Gray)
                }
            }
            Text("Distance: ${trip.distance}", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun AddTripDialog(
    destination: String,
    onDestinationChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onAddTrip: (Trip) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Add New Trip", fontWeight = FontWeight.Bold)
        },
        text = {
            Column {
                Text("Start monitoring your trip to ${destination}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("This will begin tracking your location and trip progress.")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newTrip = Trip(
                        id = UUID.randomUUID().toString(),
                        destination = destination,
                        startTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
                        endTime = null,
                        status = TripStatus.ACTIVE
                    )
                    onAddTrip(newTrip)
                }
            ) {
                Text("Start Trip")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TripMonitorScreenPreview() {
    TripMonitorScreen(navController = null)
}
