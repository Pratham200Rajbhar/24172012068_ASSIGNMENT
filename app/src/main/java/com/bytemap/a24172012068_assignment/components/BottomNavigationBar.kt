package com.bytemap.a24172012068_assignment.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val isSelected: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    currentScreen: String = "Home",
    onItemClick: (String) -> Unit = {}
) {
    val navItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, currentScreen == "Home"),
        BottomNavItem("Map", Icons.Default.Map, currentScreen == "Map"),
        BottomNavItem("Alerts", Icons.Default.Campaign, currentScreen == "Alerts"),
        BottomNavItem("Profile", Icons.Default.Person, currentScreen == "Profile")
    )
    
    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        fontWeight = if (item.isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                selected = item.isSelected,
                onClick = { onItemClick(item.title) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}
