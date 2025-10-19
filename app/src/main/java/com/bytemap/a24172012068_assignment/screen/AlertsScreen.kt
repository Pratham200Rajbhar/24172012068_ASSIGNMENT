package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlertCardUI(icon : String, title: String, description: String, date : String, alertType : String) {
    // map the icon string to a real ImageVector; fallback to Campaign
    val imageVector = when (icon) {
        "local_fire_department" -> Icons.Default.LocalFireDepartment
        "health_and_safety" -> Icons.Default.HealthAndSafety
        "traffic" -> Icons.Default.Traffic
        else -> Icons.Default.Campaign
    }

    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "Alert Icon",
                tint = Color(0xFFFF5722),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = "Date: $date",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Type: $alertType",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}

// Add a simple data holder for alerts instead of trying to build composables inside a list
data class Alert(
    val icon: String,
    val title: String,
    val description: String,
    val date: String,
    val alertType: String
)

@Composable
fun AlertsScreen() {
    // create data objects for alerts
    val alerts = listOf(
        Alert("weather_mix","Heavy Snowfall Warning", "Heavy snowfall expected in your area. Stay indoors and avoid travel if possible.", "2024-12-01", "Weather"),
        Alert("local_fire_department","Wildfire Alert", "A wildfire has been reported near your location. Follow evacuation orders and stay updated.", "2024-11-28", "Fire"),
        Alert("health_and_safety","Health Advisory", "An outbreak of flu has been reported in your community. Take necessary precautions and get vaccinated.", "2024-11-25", "Health"),
        Alert("traffic","Traffic Accident Alert", "A major traffic accident has occurred on the highway near you. Expect delays and consider alternate routes.", "2024-11-20", "Traffic")
    )

    // Use a LazyColumn so the list is scrollable
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(alerts) { alert ->
            AlertCardUI(alert.icon, alert.title, alert.description, alert.date, alert.alertType)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertsScreenPreview() {
    AlertsScreen()
}
