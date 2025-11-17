package com.bytemap.a24172012068_assignment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TripActivity(
    val time: String,
    val step: String,
    val coordinates: String,
)

@Composable
fun ActivitiesScreen(
    activities: List<TripActivity> = sampleActivities
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(activities) { activity ->
                ActivityLogLineModern(activity)
                Divider(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp),
                    color = Color(0xFFE0E4EA),
                    thickness = 1.dp
                )
            }
        }
    }
}

@Composable
fun ActivityLogLineModern(activity: TripActivity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF149EE7).copy(alpha = 0.08f))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = activity.time,
                color = Color(0xFF149EE7),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(Modifier.width(14.dp))
        Column {
            Text(
                text = activity.step,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = activity.coordinates,
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF9197A6))
            )
        }
    }
}

val sampleActivities = listOf(
    TripActivity("08:30 AM", "Trip Started", "23.474803, 72.391667"),
    TripActivity("08:45 AM", "Stopped at Gas Station", "23.480000, 72.393000"),
    TripActivity("09:00 AM", "Reached Shanku's Water Park", "23.474803, 72.391667"),
    TripActivity("09:30 AM", "Rest Stop", "23.501234, 72.398888"),
)

@Preview(showBackground = true)
@Composable
fun ActivitiesScreenPreview() {
    ActivitiesScreen()
}
