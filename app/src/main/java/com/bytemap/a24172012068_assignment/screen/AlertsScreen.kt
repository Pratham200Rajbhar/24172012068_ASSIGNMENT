package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
fun AlertCardUI(
    icon: String,
    title: String,
    description: String,
    date: String,
    alertType: String
) {
    val imageVector = when (icon) {
        "local_fire_department" -> Icons.Default.LocalFireDepartment
        "health_and_safety" -> Icons.Default.HealthAndSafety
        "traffic" -> Icons.Default.Traffic
        "weather_mix" -> Icons.Default.Campaign
        else -> Icons.Default.Campaign
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Alert Icon",
                    tint = Color(0xFF1976D2),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF22223B),
                    maxLines = 1
                )
            }
            Divider(modifier = Modifier.padding(vertical = 10.dp), color = Color(0xFFEEEEEE), thickness = 1.dp)
            Text(
                text = description,
                fontSize = 15.sp,
                color = Color(0xFF505050),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row {
                Text(
                    text = date,
                    fontSize = 12.sp,
                    color = Color(0xFF909090),
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = alertType,
                    fontSize = 12.sp,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// Data holder for alerts
data class Alert(
    val icon: String,
    val title: String,
    val description: String,
    val date: String,
    val alertType: String
)

@Composable
fun AlertsScreen() {
    val alerts = listOf(
        Alert("weather_mix", "Heavy Snowfall Warning", "Heavy snowfall expected in your area. Stay indoors and avoid travel if possible.", "2024-12-01", "Weather"),
        Alert("local_fire_department", "Wildfire Alert", "A wildfire has been reported near your location. Follow evacuation orders and stay updated.", "2024-11-28", "Fire"),
        Alert("health_and_safety", "Health Advisory", "An outbreak of flu has been reported in your community. Take necessary precautions and get vaccinated.", "2024-11-25", "Health"),
        Alert("traffic", "Traffic Accident Alert", "A major traffic accident has occurred on the highway near you. Expect delays and consider alternate routes.", "2024-11-20", "Traffic")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7F9))
            .padding(vertical = 20.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Alerts",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF22223B),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(alerts) { alert ->
                AlertCardUI(
                    alert.icon,
                    alert.title,
                    alert.description,
                    alert.date,
                    alert.alertType
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertsScreenPreview() {
    AlertsScreen()
}
