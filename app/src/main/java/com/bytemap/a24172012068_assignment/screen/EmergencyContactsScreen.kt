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

data class EmergencyContact(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val relationship: String,
    val isPrimary: Boolean = false
)

@Composable
fun EmergencyContactsScreen() {
    var showAddContact by remember { mutableStateOf(false) }
    val emergencyContacts = remember {
        mutableStateOf(
            listOf(
                EmergencyContact("1", "Mom", "+91 98765 43210", "Family", true),
                EmergencyContact("2", "Dad", "+91 98765 43211", "Family"),
                EmergencyContact("3", "Sister", "+91 98765 43212", "Family"),
                EmergencyContact("4", "Best Friend", "+91 98765 43213", "Friend"),
                EmergencyContact("5", "Police", "100", "Emergency Service"),
                EmergencyContact("6", "Ambulance", "108", "Emergency Service")
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Emergency Contacts",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                )
                Text(
                    text = "Quick access to important contacts",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            
            IconButton(
                onClick = { showAddContact = true },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF3B82F6), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Emergency Actions
        EmergencyActionsCard()
        
        Spacer(modifier = Modifier.height(16.dp))

        // Quick Call Section
        Text(
            text = "Quick Call",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A2E),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(emergencyContacts.value.filter { it.isPrimary }) { contact ->
                QuickCallCard(contact = contact)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // All Contacts Section
        Text(
            text = "All Contacts",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1A1A2E),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(emergencyContacts.value) { contact ->
                ContactCard(contact = contact)
            }
        }
    }
}

@Composable
fun EmergencyActionsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Emergency Actions",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A2E),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                EmergencyActionButton(
                    icon = Icons.Default.Emergency,
                    label = "Emergency",
                    color = Color(0xFFEF4444),
                    onClick = { /* Handle emergency call */ }
                )
                EmergencyActionButton(
                    icon = Icons.Default.LocalHospital,
                    label = "Medical",
                    color = Color(0xFF10B981),
                    onClick = { /* Handle medical emergency */ }
                )
                EmergencyActionButton(
                    icon = Icons.Default.Security,
                    label = "Police",
                    color = Color(0xFF3B82F6),
                    onClick = { /* Handle police call */ }
                )
            }
        }
    }
}

@Composable
fun EmergencyActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(56.dp)
                .background(color, CircleShape)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun QuickCallCard(contact: EmergencyContact) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF3B82F6).copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = contact.name,
                    tint = Color(0xFF3B82F6),
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = contact.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A1A2E)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF10B981).copy(alpha = 0.1f)
                    ) {
                        Text(
                            text = "Primary",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF10B981),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(
                    text = contact.phoneNumber,
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
            }
            
            IconButton(
                onClick = { /* Handle call */ },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF10B981), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call ${contact.name}",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun ContactCard(contact: EmergencyContact) {
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
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        when (contact.relationship) {
                            "Family" -> Color(0xFF3B82F6).copy(alpha = 0.1f)
                            "Friend" -> Color(0xFF10B981).copy(alpha = 0.1f)
                            "Emergency Service" -> Color(0xFFEF4444).copy(alpha = 0.1f)
                            else -> Color(0xFF6B7280).copy(alpha = 0.1f)
                        },
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (contact.relationship) {
                        "Family" -> Icons.Default.FamilyRestroom
                        "Friend" -> Icons.Default.Person
                        "Emergency Service" -> Icons.Default.Emergency
                        else -> Icons.Default.ContactPhone
                    },
                    contentDescription = contact.relationship,
                    tint = when (contact.relationship) {
                        "Family" -> Color(0xFF3B82F6)
                        "Friend" -> Color(0xFF10B981)
                        "Emergency Service" -> Color(0xFFEF4444)
                        else -> Color(0xFF6B7280)
                    },
                    modifier = Modifier.size(20.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = contact.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1A1A2E)
                )
                Text(
                    text = contact.phoneNumber,
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )
                Text(
                    text = contact.relationship,
                    fontSize = 12.sp,
                    color = Color(0xFF9CA3AF)
                )
            }
            
            Row {
                IconButton(
                    onClick = { /* Handle call */ },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFF10B981).copy(alpha = 0.1f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call",
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(4.dp))
                
                IconButton(
                    onClick = { /* Handle message */ },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFF3B82F6).copy(alpha = 0.1f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Message,
                        contentDescription = "Message",
                        tint = Color(0xFF3B82F6),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmergencyContactsScreenPreview() {
    EmergencyContactsScreen()
}
