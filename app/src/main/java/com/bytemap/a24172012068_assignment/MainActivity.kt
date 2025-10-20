package com.bytemap.a24172012068_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
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
import com.bytemap.a24172012068_assignment.components.NavDrawer
import com.bytemap.a24172012068_assignment.screen.AlertsScreen
import com.bytemap.a24172012068_assignment.screen.Dashboard
import com.bytemap.a24172012068_assignment.screen.EFirScreen
import com.bytemap.a24172012068_assignment.screen.EmergencyContactsScreen
import com.bytemap.a24172012068_assignment.screen.LocationHistoryScreen
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

    val menuItems = listOf(
        "Notifications" to "notifications",
        "File E-FIR" to "efir",
        "Trip Monitor" to "trip_monitor",
        "Location History" to "location_history",
        "Emergency Contacts" to "emergency_contacts",
        "Settings" to "settings",
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavDrawer(
                menuItems = menuItems,
                currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: "dashboard",
                onItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        val currentBackStack = navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStack.value?.destination?.route ?: "dashboard"
        val currentTitle = menuItems.find { it.second == currentRoute }?.first ?: "App"
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
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "dashboard",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("dashboard") { Dashboard(navController) }
                composable("notifications") { NotificationsScreen(navController) }
                composable("efir") { EFirScreen(navController) }
                composable("trip_monitor") {TripMonitorScreen(navController) }
                composable("location_history") { LocationHistoryScreen(navController)}
                composable("emergency_contacts") { EmergencyContactsScreen(navController) }
                composable("settings") { SettingsScreen(navController) }
            }
        }
    }
}
