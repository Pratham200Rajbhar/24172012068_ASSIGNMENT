package com.bytemap.a24172012068_assignment.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MenuItem(
    val title: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    onItemClick: (String) -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val menuItems = listOf(
        MenuItem("Notifications", Icons.Default.Notifications),
        MenuItem("File E-FIR", Icons.Default.Description),
        MenuItem("Trip Monitor", Icons.Default.TrendingUp),
        MenuItem("Location History", Icons.Default.History),
        MenuItem("Emergency Contacts", Icons.Default.ContactPhone),
        MenuItem("Settings", Icons.Default.Settings)
    )
    
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                androidx.compose.material3.Card(
                    shape = CircleShape,
                    colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color(0xFF2196F3)),
                    modifier = Modifier.size(80.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "P",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "PRATHAM RAJBHAR",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                androidx.compose.material3.Card(
                    colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Text(
                        text = "ID: 46a08ef58a11b7f86bcb9a8d97c4ac95",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            menuItems.forEach { item ->
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 16.sp
                        )
                    },
                    selected = false,
                    onClick = { onItemClick(item.title) }
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Divider()
            
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Logout"
                    )
                },
                label = {
                    Text(
                        text = "Logout",
                        fontSize = 16.sp,
                        color = Color.Red
                    )
                },
                selected = false,
                onClick = onLogoutClick
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "SafeHorizon v1.0.0",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerPreview() {
    NavigationDrawer(
        drawerState = androidx.compose.material3.rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    )
}
