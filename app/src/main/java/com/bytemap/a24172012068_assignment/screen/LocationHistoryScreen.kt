package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class LocationEntry(
    val id: String,
    val timestamp: String,
    val address: String,
    val coordinates: String,
    val duration: String,
    val type: LocationType
)

enum class LocationType {
    HOME, WORK, VISITED, TRANSIT
}

@Composable
fun LocationHistoryScreen() {
    var selectedFilter by remember { mutableStateOf("All") }
    val locationHistory = remember {
        mutableStateOf(
            listOf(
                LocationEntry("1", "Today, 6:45 PM", "123 Main Street, Downtown", "28.6139°N, 77.2090°E", "2 hours", LocationType.HOME),
                LocationEntry("2", "Today, 4:30 PM", "456 Office Complex, Business District", "28.6141°N, 77.2092°E", "8 hours", LocationType.WORK),
                LocationEntry("3", "Today, 2:00 PM", "789 Shopping Mall, Central Plaza", "28.6143°N, 77.2094°E", "1 hour", LocationType.VISITED),
                LocationEntry("4", "Today, 12:00 PM", "Highway 101, Between Exits 15-16", "28.6145°N, 77.2096°E", "30 min", LocationType.TRANSIT),
                LocationEntry("5", "Yesterday, 8:00 PM", "321 Gym Center, Fitness District", "28.6147°N, 77.2098°E", "1.5 hours", LocationType.VISITED),
                LocationEntry("6", "Yesterday, 6:00 PM", "123 Main Street, Downtown", "28.6139°N, 77.2090°E", "12 hours", LocationType.HOME),
                LocationEntry("7", "Yesterday, 9:00 AM", "456 Office Complex, Business District", "28.6141°N, 77.2092°E", "9 hours", LocationType.WORK)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Location History",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Track your location history and movements",
            fontSize = 14.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Filter Chips
        FilterChips(
            selectedFilter = selectedFilter,
            onFilterChange = { selectedFilter = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stats Summary
        LocationStatsCard()

        Spacer(modifier = Modifier.height(16.dp))

        // Location History List
        Text(
            text = "Recent Locations",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A2E),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(locationHistory.value.filter {
                selectedFilter == "All" || it.type.name == selectedFilter.uppercase()
            }) { location ->
                LocationHistoryCard(location = location)
            }
        }
    }
}

@Composable
fun FilterChips(
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    val filters = listOf("All", "Home", "Work", "Visited", "Transit")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filters.forEach { filter ->
            FilterChip(
                onClick = { onFilterChange(filter) },
                label = {
                    Text(
                        text = filter,
                        fontSize = 12.sp
                    )
                },
                selected = selectedFilter == filter,
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFF3B82F6),
                    selectedLabelColor = Color.White,
                    containerColor = Color.White
                )
            )
        }
    }
}

@Composable
fun LocationStatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Today's Summary",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A2E),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("Places Visited", "4", Icons.Default.Place, Color(0xFF3B82F6))
                StatItem("Total Time", "12h", Icons.Default.Timer, Color(0xFF10B981))
                StatItem("Distance", "45km", Icons.Default.Straighten, Color(0xFFF59E0B))
            }
        }
    }
}

@Composable
fun StatItem(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1A2E)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )
    }
}

@Composable
fun LocationHistoryCard(location: LocationEntry) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Location Type Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        when (location.type) {
                            LocationType.HOME -> Color(0xFF10B981).copy(alpha = 0.1f)
                            LocationType.WORK -> Color(0xFF3B82F6).copy(alpha = 0.1f)
                            LocationType.VISITED -> Color(0xFFF59E0B).copy(alpha = 0.1f)
                            LocationType.TRANSIT -> Color(0xFF6B7280).copy(alpha = 0.1f)
                        },
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (location.type) {
                        LocationType.HOME -> Icons.Default.Home
                        LocationType.WORK -> Icons.Default.Work
                        LocationType.VISITED -> Icons.Default.Place
                        LocationType.TRANSIT -> Icons.Default.DirectionsCar
                    },
                    contentDescription = location.type.name,
                    tint = when (location.type) {
                        LocationType.HOME -> Color(0xFF10B981)
                        LocationType.WORK -> Color(0xFF3B82F6)
                        LocationType.VISITED -> Color(0xFFF59E0B)
                        LocationType.TRANSIT -> Color(0xFF6B7280)
                    },
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = location.address,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1A1A2E)
                )
                Text(
                    text = location.timestamp,
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
                Text(
                    text = location.coordinates,
                    fontSize = 12.sp,
                    color = Color(0xFF9CA3AF)
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = location.duration,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1A1A2E)
                )
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = when (location.type) {
                        LocationType.HOME -> Color(0xFF10B981).copy(alpha = 0.1f)
                        LocationType.WORK -> Color(0xFF3B82F6).copy(alpha = 0.1f)
                        LocationType.VISITED -> Color(0xFFF59E0B).copy(alpha = 0.1f)
                        LocationType.TRANSIT -> Color(0xFF6B7280).copy(alpha = 0.1f)
                    }
                ) {
                    Text(
                        text = location.type.name,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = when (location.type) {
                            LocationType.HOME -> Color(0xFF10B981)
                            LocationType.WORK -> Color(0xFF3B82F6)
                            LocationType.VISITED -> Color(0xFFF59E0B)
                            LocationType.TRANSIT -> Color(0xFF6B7280)
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocationHistoryScreenPreview() {
    LocationHistoryScreen()
}
