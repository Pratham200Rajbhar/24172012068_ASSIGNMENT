package com.bytemap.a24172012068_assignment.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun NavDrawer(
    userName: String,
    userId: String,
    menuItems: List<NavMenuItem>,
    onItemClick: (String) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier = modifier.fillMaxHeight()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userName.first().toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = userName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ID: $userId",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Divider()
        Spacer(Modifier.height(8.dp))

        menuItems.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.label) },
                selected = item.selected,
                onClick = { onItemClick(item.route) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red)
            ) {
                Text("Logout", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "SafeHorizon v1.0.0",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class NavMenuItem(
    val label: String,
    val route: String,
    val icon: ImageVector,
    val selected: Boolean = false
)

@Preview(showBackground = true)
@Composable
fun NavDrawerPreview() {
    MaterialTheme {
        NavDrawer(
            userName = "PRATHAM RAJBHAR",
            userId = "46a08ef58a11b7f86bcb9a8d97c4ac95",
            menuItems = listOf(
                NavMenuItem("Notifications", "notifications", Icons.Default.Notifications, true),
                NavMenuItem("File E-FIR", "e-fir", Icons.Default.Description),
                NavMenuItem("Trip Monitor", "trip-monitor", Icons.Default.MonitorHeart),
                NavMenuItem("Location History", "location-history", Icons.Default.LocationOn),
                NavMenuItem("Emergency Contacts", "emergency-contacts", Icons.Default.Contacts),
                NavMenuItem("Settings", "settings", Icons.Default.Settings)
            ),
            onItemClick = {},
            onLogout = {}
        )
    }
}
