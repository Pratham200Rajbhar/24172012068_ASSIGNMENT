package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

data class EmergencyService(
    val name: String,
    val number: String,
    val description: String,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun ServicesScreen(navController: NavController) {
    val emergencyServices = listOf(
        EmergencyService(
            "Police",
            "100",
            "Emergency police assistance",
            Icons.Default.LocalPolice,
            Color(0xFF2196F3)
        ),
        EmergencyService(
            "Ambulance",
            "108",
            "Medical emergency services",
            Icons.Default.LocalHospital,
            Color(0xFF4CAF50)
        ),
        EmergencyService(
            "Fire Department",
            "101",
            "Fire and rescue services",
            Icons.Default.LocalFireDepartment,
            Color(0xFFF44336)
        ),
        EmergencyService(
            "Women Helpline",
            "1091",
            "24/7 women safety helpline",
            Icons.Default.Female,
            Color(0xFFE91E63)
        ),
        EmergencyService(
            "Child Helpline",
            "1098",
            "Child protection services",
            Icons.Default.ChildCare,
            Color(0xFFFF9800)
        ),
        EmergencyService(
            "Disaster Management",
            "108",
            "Natural disaster assistance",
            Icons.Default.Warning,
            Color(0xFF9C27B0)
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
                    imageVector = Icons.Default.Emergency,
                    contentDescription = "Emergency Services Icon",
                    tint = Color(0xFFE53E3E),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Emergency Services",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Text(
                    text = "Quick access to emergency services and helplines",
                    fontSize = 14.sp,
                    color = Color(0xFF7F8C8D),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            emergencyServices.forEach { service ->
                EmergencyServiceCard(service = service)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Quick Actions",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickActionButton(
                        title = "SOS Alert",
                        icon = Icons.Default.Warning,
                        color = Color(0xFFE53E3E),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("emergency_sos") }
                    )
                    QuickActionButton(
                        title = "File E-FIR",
                        icon = Icons.Default.Description,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("efir") }
                    )
                }
            }
        }
    }
}

@Composable
fun EmergencyServiceCard(service: EmergencyService) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = service.color.copy(alpha = 0.1f)),
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = service.icon,
                        contentDescription = "${service.name} Icon",
                        tint = service.color,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = service.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Text(
                    text = service.description,
                    fontSize = 13.sp,
                    color = Color(0xFF7F8C8D)
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = service.number,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = service.color
                )
                Text(
                    text = "Tap to call",
                    fontSize = 12.sp,
                    color = Color(0xFF7F8C8D)
                )
            }
        }
    }
}

@Composable
fun QuickActionButton(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(80.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$title Icon",
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = color,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicesScreenPreview() {
    ServicesScreen(navController = androidx.navigation.compose.rememberNavController())
}
