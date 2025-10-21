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

data class SafetyTip(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun SafetyTipsScreen(navController: NavController) {
    val safetyTips = listOf(
        SafetyTip(
            "Stay Alert",
            "Always be aware of your surroundings and trust your instincts. If something feels wrong, it probably is.",
            Icons.Default.Visibility,
            Color(0xFF4CAF50)
        ),
        SafetyTip(
            "Share Location",
            "Keep your location sharing enabled and let trusted contacts know where you're going.",
            Icons.Default.LocationOn,
            Color(0xFF2196F3)
        ),
        SafetyTip(
            "Emergency Contacts",
            "Keep emergency contacts easily accessible and know how to reach them quickly.",
            Icons.Default.Contacts,
            Color(0xFFFF9800)
        ),
        SafetyTip(
            "Avoid Dark Areas",
            "Stick to well-lit, populated areas especially during night time. Plan your routes in advance.",
            Icons.Default.LightMode,
            Color(0xFF9C27B0)
        ),
        SafetyTip(
            "Trust Your Phone",
            "Keep your phone charged and easily accessible. Use emergency features when needed.",
            Icons.Default.Phone,
            Color(0xFFF44336)
        ),
        SafetyTip(
            "Travel in Groups",
            "When possible, travel with friends or family. There's safety in numbers.",
            Icons.Default.Group,
            Color(0xFF607D8B)
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
                    imageVector = Icons.Default.Security,
                    contentDescription = "Safety Icon",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Safety Tips",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Text(
                    text = "Essential safety guidelines to keep you protected",
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
            safetyTips.forEach { tip ->
                SafetyTipCard(tip = tip)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE53E3E)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Emergency Icon",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "In Emergency?",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Use the SOS button on the main screen",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun SafetyTipCard(tip: SafetyTip) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                colors = CardDefaults.cardColors(containerColor = tip.color.copy(alpha = 0.1f)),
                modifier = Modifier.size(48.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = tip.icon,
                        contentDescription = "${tip.title} Icon",
                        tint = tip.color,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = tip.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tip.description,
                    fontSize = 14.sp,
                    color = Color(0xFF7F8C8D),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SafetyTipsScreenPreview() {
    SafetyTipsScreen(navController = androidx.navigation.compose.rememberNavController())
}
