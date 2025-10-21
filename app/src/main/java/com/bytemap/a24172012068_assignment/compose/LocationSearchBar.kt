package com.bytemap.a24172012068_assignment.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bytemap.a24172012068_assignment.utils.GeocodingService
import com.bytemap.a24172012068_assignment.utils.LocationResult
import kotlinx.coroutines.delay
import org.osmdroid.util.GeoPoint

@Composable
fun LocationSearchBar(
    modifier: Modifier = Modifier,
    onLocationSelected: (LocationResult) -> Unit,
    onCurrentLocationRequested: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    var searchResults by remember { mutableStateOf<List<LocationResult>>(emptyList()) }
    var showResults by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    
    val context = LocalContext.current
    val geocodingService = remember { GeocodingService(context) }
    
    // Debounce search with faster response
    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty() && searchQuery.length > 1) {
            delay(300) // Faster response - 300ms
            isSearching = true
            val results = geocodingService.searchLocation(searchQuery)
            searchResults = results
            showResults = true
            isSearching = false
        } else {
            searchResults = emptyList()
            showResults = false
        }
    }
    
    Column(modifier = modifier) {
        // Enhanced search input field
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isFocused) Color.White else Color.White.copy(alpha = 0.95f)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = if (isFocused) 8.dp else 4.dp
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = if (isFocused) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier.size(22.dp)
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { newValue -> searchQuery = newValue },
                    placeholder = { 
                        Text(
                            text = "Search places, cities, landmarks...",
                            color = Color.Gray.copy(alpha = 0.7f),
                            fontSize = 15.sp
                        ) 
                    },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    ),
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                
                if (searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { 
                            searchQuery = ""
                            showResults = false
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                IconButton(
                    onClick = onCurrentLocationRequested,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            RoundedCornerShape(8.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.MyLocation,
                        contentDescription = "Current Location",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        
        // Enhanced search results
        if (showResults && searchResults.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .heightIn(max = 250.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(searchResults.take(5)) { result -> // Limit to 5 results
                        SearchResultItem(
                            result = result,
                            onClick = {
                                onLocationSelected(result)
                                searchQuery = result.name
                                showResults = false
                            }
                        )
                    }
                }
            }
        }
        
        // Loading indicator
        if (isSearching) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Searching...",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    result: LocationResult,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(22.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = result.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = result.address,
                fontSize = 13.sp,
                color = Color.Gray.copy(alpha = 0.8f),
                maxLines = 2,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
        }
        
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Navigate",
            tint = Color.Gray.copy(alpha = 0.6f),
            modifier = Modifier.size(16.dp)
        )
    }
}
