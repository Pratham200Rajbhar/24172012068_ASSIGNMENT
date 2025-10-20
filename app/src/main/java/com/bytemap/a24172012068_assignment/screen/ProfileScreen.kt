package com.bytemap.a24172012068_assignment.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        UserIDCard()
        ContactUI()
        LocationUI()

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                Toast.makeText(context, "Logged Out", Toast.LENGTH_SHORT).show()
            },
            border = BorderStroke(1.dp, Color.Red)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = "Logout Icon",
                tint = Color.Red,
                modifier = Modifier
                    .size(28.dp)
                    .padding(4.dp)
            )
            Text(
                text = "Logout",
                fontSize = 16.sp,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Composable
fun UserIDCard() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Icon",
                    tint = Color(0xFF2196F3),
                    modifier = Modifier
                        .size(70.dp)
                        .padding(16.dp),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "USER 1",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Text(
                    text = "ID: 46a08ef58a11b7f86bcb9a8d97c4ac95",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


@Composable
fun ContactUI() {
    val contactDetails = listOf(
        Triple("Phone", "+91 9512518403", Icons.Default.Phone),
        Triple("Email", "user1@gmail.com", Icons.Default.Mail),
        Triple("Registered", "Today", Icons.Default.CalendarToday)
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Contact Information",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Loop through each contact detail
            contactDetails.forEachIndexed { index, (label, value, icon) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "$label Icon",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = label,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = value,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun LocationUI() {
    val contactDetails = listOf(
        Triple("Location Service", "Disabled", Icons.Default.LocationOn),
        Triple("Permission", "Unknown", Icons.Default.Security),
        Triple("Tracking", "Inactive", Icons.Default.TrackChanges)
    )

    // Define colors for each status
    val statusColors = mapOf(
        "Disabled" to Color(0xFFD32F2F),  // Red
        "Unknown" to Color(0xFFFBC02D),   // Yellow
        "Inactive" to Color(0xFF9E9E9E)   // Gray
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Location Settings",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            contactDetails.forEach { (label, value, icon) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "$label Icon",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = label,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = value,
                            fontSize = 16.sp,
                            color = statusColors[value] ?: Color.Black
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
