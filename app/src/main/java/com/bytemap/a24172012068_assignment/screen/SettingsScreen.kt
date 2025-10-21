package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class SettingsItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val iconColor: Color,
    val onClick: () -> Unit = {}
)

@Composable
fun SettingsScreen(navController: NavController) {
    var locationEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var autoSOSEnabled by remember { mutableStateOf(false) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var biometricEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .verticalScroll(rememberScrollState())
    ) {
        SettingsHeader()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        SettingsSection(
            title = "Safety Settings",
            items = listOf(
                SettingsItem(
                    title = "Location Services",
                    subtitle = "Share your location for emergency response",
                    icon = Icons.Default.LocationOn,
                    iconColor = Color(0xFF2196F3),
                    onClick = { locationEnabled = !locationEnabled }
                ),
                SettingsItem(
                    title = "Auto SOS",
                    subtitle = "Automatically send SOS in critical situations",
                    icon = Icons.Default.Warning,
                    iconColor = Color(0xFFE53E3E),
                    onClick = { autoSOSEnabled = !autoSOSEnabled }
                ),
                SettingsItem(
                    title = "Biometric Lock",
                    subtitle = "Secure app with fingerprint or face unlock",
                    icon = Icons.Default.Fingerprint,
                    iconColor = Color(0xFF4CAF50),
                    onClick = { biometricEnabled = !biometricEnabled }
                )
            )
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        SettingsSection(
            title = "Notifications",
            items = listOf(
                SettingsItem(
                    title = "Push Notifications",
                    subtitle = "Receive alerts and safety updates",
                    icon = Icons.Default.Notifications,
                    iconColor = Color(0xFFFF9800),
                    onClick = { notificationsEnabled = !notificationsEnabled }
                ),
                SettingsItem(
                    title = "Emergency Alerts",
                    subtitle = "Critical safety notifications",
                    icon = Icons.Default.NotificationImportant,
                    iconColor = Color(0xFFE53E3E),
                    onClick = { }
                )
            )
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        SettingsSection(
            title = "Privacy & Security",
            items = listOf(
                SettingsItem(
                    title = "Data Privacy",
                    subtitle = "Manage your personal data",
                    icon = Icons.Default.PrivacyTip,
                    iconColor = Color(0xFF9C27B0),
                    onClick = { }
                ),
                SettingsItem(
                    title = "Account Security",
                    subtitle = "Password and security settings",
                    icon = Icons.Default.Security,
                    iconColor = Color(0xFF607D8B),
                    onClick = { }
                )
            )
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        SettingsSection(
            title = "App Settings",
            items = listOf(
                SettingsItem(
                    title = "Dark Mode",
                    subtitle = "Switch between light and dark themes",
                    icon = Icons.Default.DarkMode,
                    iconColor = Color(0xFF424242),
                    onClick = { darkModeEnabled = !darkModeEnabled }
                ),
                SettingsItem(
                    title = "Language",
                    subtitle = "English",
                    icon = Icons.Default.Language,
                    iconColor = Color(0xFF2196F3),
                    onClick = { }
                ),
                SettingsItem(
                    title = "About",
                    subtitle = "App version and information",
                    icon = Icons.Default.Info,
                    iconColor = Color(0xFF757575),
                    onClick = { }
                )
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LogoutButton()
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun SettingsHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = Color(0xFF1937B3),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Settings",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C3E50)
                )
                Text(
                    text = "Customize your SafeHorizon experience",
                    fontSize = 14.sp,
                    color = Color(0xFF7F8C8D)
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    items: List<SettingsItem>
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF2C3E50),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
        )
        
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                items.forEachIndexed { index, item ->
                    SettingsItemRow(
                        item = item,
                        showDivider = index < items.size - 1
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsItemRow(
    item: SettingsItem,
    showDivider: Boolean
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = item.iconColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2C3E50)
                )
                Text(
                    text = item.subtitle,
                    fontSize = 13.sp,
                    color = Color(0xFF7F8C8D)
                )
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigate",
                tint = Color(0xFFBDC3C7),
                modifier = Modifier.size(20.dp)
            )
        }
        
        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color(0xFFE5E7EB)
            )
        }
    }
}

@Composable
fun LogoutButton() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0xFFE53E3E).copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = Color(0xFFE53E3E),
                    modifier = Modifier.size(20.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Logout",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFE53E3E)
                )
                Text(
                    text = "Sign out of your account",
                    fontSize = 13.sp,
                    color = Color(0xFF7F8C8D)
                )
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Navigate",
                tint = Color(0xFFBDC3C7),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen(navController = androidx.navigation.compose.rememberNavController())
    }
}