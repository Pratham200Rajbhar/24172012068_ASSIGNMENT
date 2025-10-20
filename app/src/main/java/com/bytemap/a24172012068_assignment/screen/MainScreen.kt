package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.material3.rememberDrawerState
import com.bytemap.a24172012068_assignment.components.AppBar
import com.bytemap.a24172012068_assignment.components.BottomNavigationBar
import com.bytemap.a24172012068_assignment.components.NavigationDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("Home") }
    val context = LocalContext.current
    val drawerState =
        rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawer(
                drawerState = drawerState,
                onItemClick = { item ->
                    scope.launch {
                        drawerState.close()
                    }
                    // Navigate to the appropriate screen based on drawer item
                    when (item) {
                        "Notifications" -> currentScreen = "Alerts"
                        "File E-FIR" -> currentScreen = "EFir"
                        "Trip Monitor" -> currentScreen = "TripMonitor"
                        "Location History" -> currentScreen = "LocationHistory"
                        "Emergency Contacts" -> currentScreen = "EmergencyContacts"
                        "Settings" -> currentScreen = "Settings"
                    }
                },
                onLogoutClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    Toast.makeText(context, "Logout clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                AppBar(
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onNotificationClick = {
                        Toast.makeText(context, "Notifications clicked!", Toast.LENGTH_SHORT).show()
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar(
                    currentScreen = currentScreen,
                    onItemClick = { item ->
                        currentScreen = item
                        Toast.makeText(context, "$item clicked!", Toast.LENGTH_SHORT).show()
                    }
                )
            },
            containerColor = Color(0xFFF8F9FA)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF8F9FA))
            ) {
                when (currentScreen) {
                    "Home" -> HomeScreen()
                    "Map" -> MapScreen()
                    "Alerts" -> AlertsScreen()
                    "Profile" -> ProfileScreen()
                    "EFir" -> EFirScreen()
                    "TripMonitor" -> TripMonitorScreen()
                    "LocationHistory" -> LocationHistoryScreen()
                    "EmergencyContacts" -> EmergencyContactsScreen()
                    "Settings" -> SettingsScreen()
                    else -> HomeScreen()
                }
            }
        }
    }
}
