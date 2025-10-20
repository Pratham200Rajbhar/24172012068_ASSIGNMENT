package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripMonitorScreen(navController: NavController?) {
    var selectedTab by remember { mutableStateOf(0) }
    var destination by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                divider = {}) {
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
        }
        Spacer(Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF039be5)
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
        Spacer(Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF039be5))
                    Spacer(Modifier.width(8.dp))
                    Text("Set Destination", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                }
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = destination,
                    onValueChange = { destination = it },
                    placeholder = { Text("Enter destination name", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF039be5)),
                    onClick = { }
                ) {
                    Text("Search Destination", color = Color.White, fontSize = 16.sp)
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.PauseCircle,
                        contentDescription = null,
                        tint = Color(0xFF616161)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Trip Status", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                }
                Spacer(Modifier.height(8.dp))
                Text("Monitoring Inactive", color = Color.Gray)
                Spacer(Modifier.height(8.dp))
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    onClick = { },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF43A047))
                ) {
                    Text("Start Monitoring", color = Color(0xFF43A047), fontSize = 16.sp)
                }
                Spacer(Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF039be5))
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, tint = Color(0xFF039be5))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Refresh Now", color = Color(0xFF039be5), fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripMonitorScreenPreview() {
    TripMonitorScreen(navController = null)
}
