package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SettingsItem(
    val title: String,
    val subtitle: String? = null,
    val icon: ImageVector,
    val type: SettingsType,
    val value: String? = null
)

enum class SettingsType {
    TOGGLE, NAVIGATION, INFO
}

@Composable
fun SettingsScreen() {
    var locationEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var autoBackupEnabled by remember { mutableStateOf(false) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    val settingsSections = listOf(
        listOf(
            SettingsItem(
                "Location Services",
                "Allow app to access your location",
                Icons.Default.LocationOn,
                SettingsType.TOGGLE
            ),
            SettingsItem(
                "Push Notifications",
                "Receive alerts and updates",
                Icons.Default.Notifications,
                SettingsType.TOGGLE
            ),
            SettingsItem(
                "Auto Backup",
                "Automatically backup your data",
                Icons.Default.Backup,
                SettingsType.TOGGLE
            ),
            SettingsItem(
                "Dark Mode",
                "Use dark theme",
                Icons.Default.DarkMode,
                SettingsType.TOGGLE
            )
        ),
        listOf(
            SettingsItem(
                "Privacy Policy",
                "Read our privacy policy",
                Icons.Default.PrivacyTip,
                SettingsType.NAVIGATION
            ),
            SettingsItem(
                "Terms of Service",
                "Read our terms of service",
                Icons.Default.Description,
                SettingsType.NAVIGATION
            ),
            SettingsItem(
                "Help & Support",
                "Get help and contact support",
                Icons.Default.Help,
                SettingsType.NAVIGATION
            ),
            SettingsItem(
                "About",
                "App version and information",
                Icons.Default.Info,
                SettingsType.NAVIGATION
            )
        ),
        listOf(
            SettingsItem(
                "Account",
                "Manage your account settings",
                Icons.Default.AccountCircle,
                SettingsType.NAVIGATION
            ),
            SettingsItem(
                "Security",
                "Password and security settings",
                Icons.Default.Security,
                SettingsType.NAVIGATION
            ),
            SettingsItem(
                "Data Usage",
                "Monitor your data consumption",
                Icons.Default.DataUsage,
                SettingsType.NAVIGATION
            )
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Settings",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Customize your app experience",
            fontSize = 14.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Profile Section
        ProfileSection()
        
        Spacer(modifier = Modifier.height(24.dp))

        // Settings Sections
        settingsSections.forEachIndexed { index, section ->
            SettingsSection(
                title = when (index) {
                    0 -> "Preferences"
                    1 -> "Legal & Support"
                    else -> "Account & Security"
                },
                items = section,
                onToggleChange = { item ->
                    when (item.title) {
                        "Location Services" -> locationEnabled = !locationEnabled
                        "Push Notifications" -> notificationsEnabled = !notificationsEnabled
                        "Auto Backup" -> autoBackupEnabled = !autoBackupEnabled
                        "Dark Mode" -> darkModeEnabled = !darkModeEnabled
                    }
                },
                onItemClick = { item ->
                    // Handle navigation
                }
            )
            
            if (index < settingsSections.size - 1) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // App Version
        AppVersionCard()
    }
}

@Composable
fun ProfileSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                    .background(Color(0xFF3B82F6).copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color(0xFF3B82F6),
                    modifier = Modifier.size(30.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Pratham Rajbhar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1A1A2E)
                )
                Text(
                    text = "user1@gmail.com",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            }
            
            IconButton(
                onClick = { /* Handle edit profile */ },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFF3F4F6), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile",
                    tint = Color(0xFF6B7280),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    items: List<SettingsItem>,
    onToggleChange: (SettingsItem) -> Unit,
    onItemClick: (SettingsItem) -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A2E),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                items.forEachIndexed { index, item ->
                    SettingsItemRow(
                        item = item,
                        onToggleChange = onToggleChange,
                        onItemClick = onItemClick
                    )
                    
                    if (index < items.size - 1) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color(0xFFE5E7EB),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsItemRow(
    item: SettingsItem,
    onToggleChange: (SettingsItem) -> Unit,
    onItemClick: (SettingsItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = Color(0xFF6B7280),
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1A1A2E)
            )
            item.subtitle?.let { subtitle ->
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            }
        }
        
        when (item.type) {
            SettingsType.TOGGLE -> {
                Switch(
                    checked = when (item.title) {
                        "Location Services" -> true
                        "Push Notifications" -> true
                        "Auto Backup" -> false
                        "Dark Mode" -> false
                        else -> false
                    },
                    onCheckedChange = { onToggleChange(item) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF3B82F6),
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0xFFD1D5DB)
                    )
                )
            }
            SettingsType.NAVIGATION -> {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Navigate",
                    tint = Color(0xFF9CA3AF),
                    modifier = Modifier.size(20.dp)
                )
            }
            SettingsType.INFO -> {
                Text(
                    text = item.value ?: "",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            }
        }
    }
}

@Composable
fun AppVersionCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Shield,
                contentDescription = "App Icon",
                tint = Color(0xFF3B82F6),
                modifier = Modifier.size(32.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "SafeHorizon",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A2E)
            )
            
            Text(
                text = "Version 1.0.0",
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "© 2024 SafeHorizon. All rights reserved.",
                fontSize = 12.sp,
                color = Color(0xFF9CA3AF)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
