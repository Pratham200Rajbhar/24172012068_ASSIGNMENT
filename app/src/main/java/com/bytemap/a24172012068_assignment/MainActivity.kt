package com.bytemap.a24172012068_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bytemap.a24172012068_assignment.components.AppBar
import com.bytemap.a24172012068_assignment.components.BottomBar
import com.bytemap.a24172012068_assignment.components.NavDrawer
import com.bytemap.a24172012068_assignment.components.NavMenuItem
import com.bytemap.a24172012068_assignment.screen.AlertsScreen
import com.bytemap.a24172012068_assignment.screen.Dashboard
import com.bytemap.a24172012068_assignment.screen.EFirScreen
import com.bytemap.a24172012068_assignment.screen.EmergencyContactsScreen
import com.bytemap.a24172012068_assignment.screen.LocationHistoryScreen
import com.bytemap.a24172012068_assignment.screen.MapScreen
import com.bytemap.a24172012068_assignment.screen.NotificationsScreen
import com.bytemap.a24172012068_assignment.screen.ProfileScreen
import com.bytemap.a24172012068_assignment.screen.SettingsScreen
import com.bytemap.a24172012068_assignment.screen.TripMonitorScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val userName = "PRATHAM RAJBHAR"
    val userId = "46a08ef58a11b7f86bcb9a8d97c4ac95"

    val menuItems = listOf(
        NavMenuItem("Notifications", "notifications", Icons.Default.Notifications, false),
        NavMenuItem("File E-FIR", "efir", Icons.Default.Description, false),
        NavMenuItem("Trip Monitor", "trip_monitor", Icons.Default.MonitorHeart, false),
        NavMenuItem("Location History", "location_history", Icons.Default.LocationOn, false),
        NavMenuItem("Emergency Contacts", "emergency_contacts", Icons.Default.Contacts, false),
        NavMenuItem("Settings", "settings", Icons.Default.Settings, false)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavDrawer(
                userName = userName,
                userId = userId,
                menuItems = menuItems.map {
                    it.copy(selected = navController.currentBackStackEntryAsState().value?.destination?.route == it.route)
                },
                onItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch { drawerState.close() }
                },
                onLogout = {
                }
            )
        }
    ) {
        val currentBackStack = navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStack.value?.destination?.route ?: "dashboard"
        
        val bottomBarTitles = mapOf(
            "dashboard" to "Home",
            "map" to "Map",
            "alerts" to "Alerts",
            "profile" to "Profile"
        )
        
        val currentTitle = bottomBarTitles[currentRoute] 
            ?: menuItems.find { it.route == currentRoute }?.label 
            ?: "App"
        val canPop = navController.previousBackStackEntry != null

        Scaffold(
            topBar = {
                AppBar(
                    title = currentTitle,
                    canPop = canPop,
                    onNavIconClick = {
                        if (canPop) {
                            navController.popBackStack()
                        } else {
                            scope.launch { drawerState.open() }
                        }
                    }
                )
            },
            bottomBar = {
                BottomBar(navController = navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "dashboard",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("dashboard") { Dashboard(navController) }
                composable("map") { MapScreen() }
                composable("alerts") { AlertsScreen() }
                composable("notifications") { NotificationsScreen(navController) }
                composable("profile") { ProfileScreen() }
                composable("efir") { EFirScreen(navController) }
                composable("trip_monitor") { TripMonitorScreen(navController) }
                composable("location_history") { LocationHistoryScreen(navController) }
                composable("emergency_contacts") { EmergencyContactsScreen(navController) }
                composable("settings") { SettingsScreen(navController) }
            }
        }
    }
}
