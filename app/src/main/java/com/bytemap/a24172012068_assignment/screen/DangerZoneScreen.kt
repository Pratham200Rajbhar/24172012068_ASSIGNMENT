package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class DangerZone(
    val name: String,
    val riskLevel: String,
    val description: String,
    val lastReported: String,
    val distance: String,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun DangerZoneScreen(navController: NavController) {
    val dangerZones = listOf(
        DangerZone(
            "Downtown Market Area",
            "High Risk",
            "Multiple incidents reported in the last 24 hours",
            "2 hours ago",
            "1.2 km",
            Icons.Default.Warning,
            Color(0xFFE53E3E)
        ),
        DangerZone(
            "Industrial Zone",
            "Medium Risk",
            "Isolated area with limited lighting",
            "6 hours ago",
            "3.5 km",
            Icons.Default.Construction,
            Color(0xFFFF9800)
        ),
        DangerZone(
            "Parking Lot - Mall",
            "High Risk",
            "Recent theft incidents reported",
            "4 hours ago",
            "2.1 km",
            Icons.Default.LocalParking,
            Color(0xFFE53E3E)
        ),
        DangerZone(
            "Subway Station",
            "Low Risk",
            "Generally safe but stay alert",
            "1 day ago",
            "0.8 km",
            Icons.Default.Train,
            Color(0xFF4CAF50)
        ),
        DangerZone(
            "Night Club District",
            "Medium Risk",
            "Late night incidents common",
            "8 hours ago",
            "4.2 km",
            Icons.Default.Nightlife,
            Color(0xFFFF9800)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Danger Zone Icon",
                    tint = Color(0xFFE53E3E),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Danger Zones",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Text(
                    text = "Areas with reported safety incidents",
                    fontSize = 14.sp,
                    color = Color(0xFF7F8C8D),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Risk Levels",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    RiskLevelIndicator("High", Color(0xFFE53E3E))
                    RiskLevelIndicator("Medium", Color(0xFFFF9800))
                    RiskLevelIndicator("Low", Color(0xFF4CAF50))
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            dangerZones.forEach { zone ->
                DangerZoneCard(zone = zone)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "Safety Icon",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Stay Safe",
                    color = Color(0xFF1976D2),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Avoid high-risk areas when possible. If you must go, stay alert and share your location.",
                    color = Color(0xFF1976D2),
                    fontSize = 14.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun RiskLevelIndicator(level: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color, RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = level,
            fontSize = 12.sp,
            color = Color(0xFF7F8C8D)
        )
    }
}

@Composable
fun DangerZoneCard(zone: DangerZone) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = zone.color.copy(alpha = 0.1f)),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = zone.icon,
                                contentDescription = "${zone.name} Icon",
                                tint = zone.color,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Column {
                        Text(
                            text = zone.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2C3E50)
                        )
                        Text(
                            text = zone.distance,
                            fontSize = 12.sp,
                            color = Color(0xFF7F8C8D)
                        )
                    }
                }
                
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = zone.color.copy(alpha = 0.1f))
                ) {
                    Text(
                        text = zone.riskLevel,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = zone.color,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = zone.description,
                fontSize = 14.sp,
                color = Color(0xFF7F8C8D)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Last reported: ${zone.lastReported}",
                fontSize = 12.sp,
                color = Color(0xFF95A5A6),
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DangerZoneScreenPreview() {
    DangerZoneScreen(navController = androidx.navigation.compose.rememberNavController())
}
